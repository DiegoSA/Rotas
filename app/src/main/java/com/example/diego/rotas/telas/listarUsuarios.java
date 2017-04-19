package com.example.diego.rotas.telas;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.diego.rotas.R;
import com.example.diego.rotas.banco.DBController;
import com.example.diego.rotas.banco.DBHelper;

public class listarUsuarios extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);


        DBController crud = new DBController(getBaseContext());
        Cursor cursor = crud.listarUsuarios();

        String[] campos = new String[] {DBHelper.login};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.activity_listar_usuarios, cursor, campos, null, 0);

        lista = (ListView) findViewById(R.id.ListViewUsuarios);
        lista.setAdapter(adapter);
    }
}
