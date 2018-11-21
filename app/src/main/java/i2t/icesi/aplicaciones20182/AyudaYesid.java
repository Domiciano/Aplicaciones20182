package i2t.icesi.aplicaciones20182;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class AyudaYesid extends AppCompatActivity {

    FirebaseDatabase db;
    FirebaseStorage storage;
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_yesid);
        db = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        imageview = findViewById(R.id.imageview);

        DatabaseReference ref = db.getReference().child("alertas");

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                //AQUI PUSE DIRECTAMENTE EL NOMBRE DE LA FOTO CONOCIDA, TIENE EL JPG PORQUE ASÍ LA SUBI
                //SI USTEDES LA SUBIERON SIN EXTENSIÓN NO HAY PROBLEMA
                StorageReference imagen = storage.getReference().child("comments").child("yesid.jpg");
                imagen.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(AyudaYesid.this)
                                .load(uri)
                                .into(imageview);
                    }
                });
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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

    }
}
