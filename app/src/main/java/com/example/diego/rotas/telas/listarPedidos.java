package com.example.diego.rotas.telas;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.diego.rotas.R;
import com.example.diego.rotas.banco.DBController;
import com.example.diego.rotas.banco.Teste;

public class listarPedidos extends AppCompatActivity {

    private Button btRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pedidos);

        btRefresh = (Button) findViewById(R.id.buttonRefresh);
        btRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
