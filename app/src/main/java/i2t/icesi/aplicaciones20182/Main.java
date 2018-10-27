package i2t.icesi.aplicaciones20182;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import i2t.icesi.aplicaciones20182.model.Comentario;

public class Main extends AppCompatActivity {

    private static final int REQUEST_GALLERY = 101;
    FirebaseAuth auth;
    FirebaseDatabase db;

    Button btn_comentar;
    EditText et_comentario;
    ListView lista_comentarios;
    Adaptador adaptador;

    Button btn_agregarfoto;
    ImageView img_foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() == null){
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }
        btn_agregarfoto = findViewById(R.id.btn_agregarfoto);
        img_foto = findViewById(R.id.img_foto);

        btn_comentar = findViewById(R.id.btn_comentar);
        et_comentario = findViewById(R.id.et_comentario);
        lista_comentarios = findViewById(R.id.lista_comentarios);
        adaptador = new Adaptador(this);
        lista_comentarios.setAdapter(adaptador);


        DatabaseReference comentarios_ref = db.getReference().child("comentarios");


        comentarios_ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Comentario c = dataSnapshot.getValue(Comentario.class);
                adaptador.agregarComentario(c);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Comentario c = dataSnapshot.getValue(Comentario.class);
                adaptador.refrescarComentario(c);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        btn_comentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comentario = et_comentario.getText().toString();
                if(comentario.isEmpty()) return;
                DatabaseReference reference = db.getReference().child("comentarios").push();
                String id_comment = reference.getKey();

                Comentario c = new Comentario();
                c.setId(id_comment);
                c.setTexto(comentario);
                reference.setValue(c);
            }
        });

        btn_agregarfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setType("image/*");
                //i.setType("video/*");
                //i.setType("*/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i, REQUEST_GALLERY);

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_GALLERY && resultCode == RESULT_OK){
            //ACCIONES QUE HAREMOS EN 15 DIAS
        }
    }
}
