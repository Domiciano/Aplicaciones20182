package i2t.icesi.aplicaciones20182;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText et_email;
    private EditText et_pass;
    private Button btn_login;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        et_email = findViewById(R.id.et_email);
        et_pass = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);


        if(auth.getCurrentUser() != null){
            Intent i = new Intent(this, Main.class);
            startActivity(i);
            finish();
            return;
        }


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUsuario(et_email.getText().toString(), et_pass.getText().toString());
            }
        });

    }

    private void loginUsuario(String email, String pass) {
        auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent i = new Intent(Login.this, Main.class);
                            startActivity(i);
                            finish();
                            Toast.makeText(Login.this,"Login correcto", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Login.this,"Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
