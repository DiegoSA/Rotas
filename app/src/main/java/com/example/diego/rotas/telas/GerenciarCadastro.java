package com.example.diego.rotas.telas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.diego.rotas.R;

public class GerenciarCadastro extends AppCompatActivity {

    private Button btListar;
    private ListView listarUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_cadastro);
        btListar = (Button) findViewById(R.id.buttonListar);
        listarUsers = (ListView) findViewById(R.id.ListUsers);

        btListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}
