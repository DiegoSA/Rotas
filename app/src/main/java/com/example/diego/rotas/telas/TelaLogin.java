package com.example.diego.rotas.telas;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diego.rotas.R;
import com.example.diego.rotas.banco.DBController;

public class TelaLogin extends AppCompatActivity {
    private EditText login;
    private EditText senha;
    private Button btLogin;
    private DBController crud;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        login = (EditText) findViewById(R.id.editTextLogin);
        senha = (EditText) findViewById(R.id.editTextSenha);
        btLogin = (Button) findViewById(R.id.buttonLogin);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user, password, logado = "nao";
                crud = new DBController(getBaseContext());
                cursor = crud.listarUsuarios();
                do {
                    user = (String) cursor.getString(cursor.getColumnIndexOrThrow("login"));
                    password = (String) cursor.getString(cursor.getColumnIndexOrThrow("senha"));
                    if(login.getText().toString().equals(user)){
                        if(senha.getText().toString().equals(password)){
                            logado = "sim";
                            Intent i = new Intent(getBaseContext(), TelaPrincipal.class);
                            startActivity(i);
                            finish();
                        }
                    }
                }while (cursor.moveToNext());

                if(logado.equals("nao")){
                    Toast.makeText(getBaseContext(), "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
