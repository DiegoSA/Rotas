package com.example.diego.rotas.telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.diego.rotas.MapsActivity;
import com.example.diego.rotas.R;

public class TelaPrincipalAdm extends AppCompatActivity {

    ImageButton cadastrar;
    ImageButton mapa;
    ImageButton listarUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        cadastrar = (ImageButton) findViewById(R.id.imageButtonCadastrar);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TelaPrincipalAdm.this, cadastrarUsuario.class);
                startActivity(i);
            }
        });

        mapa = (ImageButton) findViewById(R.id.imageButtonMapa);
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TelaPrincipalAdm.this, MapsActivity.class);
                startActivity(i);
            }
        });

        listarUsuarios = (ImageButton) findViewById(R.id.imageButtonUsuarios);
        listarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TelaPrincipalAdm.this, com.example.diego.rotas.telas.listarUsuarios.class);
                startActivity(i);
            }
        });
    }
}
