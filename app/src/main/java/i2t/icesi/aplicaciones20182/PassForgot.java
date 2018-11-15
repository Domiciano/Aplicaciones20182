package i2t.icesi.aplicaciones20182;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PassForgot extends AppCompatActivity {

    private EditText email_forgot;
    private Button submit_forgot;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_forgot);

        auth = FirebaseAuth.getInstance();
        email_forgot = findViewById(R.id.email_forgot);
        submit_forgot = findViewById(R.id.submit_forgot);

        submit_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = email_forgot.getText().toString().trim();
                if(email.isEmpty()){
                    Toast.makeText(PassForgot.this, "Debe escribir un correo", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(PassForgot.this, "El correo se envió a "+email, Toast.LENGTH_LONG).show();
                            finish();
                        }else{
                            Toast.makeText(PassForgot.this, "Ocurrió un error", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }
}
