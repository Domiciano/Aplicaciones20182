package i2t.icesi.aplicaciones20182;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import i2t.icesi.aplicaciones20182.model.Comentario;

/**
 * Created by Domiciano on 01/05/2018.
 */

/*
    PARA USAR ESTE ADAPTADOR DEBE TENER EL ARCHIVO renglon.xml EN LA CARPETA LAYOUT
    DEBE TAMBIÉN USAR 
*/
public class Adaptador extends BaseAdapter{
    ArrayList<Comentario> comentarios;
    Context context;

    public Adaptador(Context context){
        this.context = context;
        comentarios = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return comentarios.size();
    }

    @Override
    public Object getItem(int position) {
        return comentarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View renglon = inflater.inflate(R.layout.renglon,null);
        TextView tv_comentario = renglon.findViewById(R.id.tv_comentario);
        Button like_btn = renglon.findViewById(R.id.like_btn);
        like_btn.setText(""+comentarios.get(position).getLikes().size());

        tv_comentario.setText(comentarios.get(position).getTexto());
        like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                db.getReference().child("comentarios").child(comentarios.get(position).getId()).child("likes").push().setValue("L");
            }
        });

        return renglon;
    }

    public void agregarComentario(Comentario c) {
        comentarios.add(c);
        notifyDataSetChanged();
    }

    public void refrescarComentario(Comentario c) {
        int index = comentarios.indexOf(c);
        Comentario viejo = comentarios.get(index);
        viejo.setLikes(c.getLikes());
        notifyDataSetChanged();
    }
}
