package br.com.monkey.appfirebaseautenticacao;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registro extends AppCompatActivity {
    private EditText emailText, passwordText;
    private Button cadastrar;
    private FirebaseAuth meuAuth;

    @Override
    protected void OnCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_registro);

        emailText = findViewById(R.id.etEmail);
        passwordText = findViewById(R.id.etSenha);
        cadastrar = findViewById(R.id.btnCadastrar);

        meuAuth = FirebaseAuth.getInstance();

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criaUser();
            }

            private void criaUser(){
                String email = emailText.getText().toString();
                String senha = passwordText.getText().toString();

                if (TextUtils.isEmpty(email)){
                    emailText.setError("Email não pode ser vazio!");
                    emailText.requestFocus();
                }else if(TextUtils.isEmpty(senha)){
                    passwordText.setError("Senha não pode ser vazia!");
                    passwordText.requestFocus();
                }else{
                    meuAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Registro.this,"CADASTRADO COM SUCESSO!", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(Registro.this,"ERRO AO CADASTRAR!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
