package com.example.diego.rotas.telas;

import android.app.ListActivity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.diego.rotas.R;
import com.example.diego.rotas.auxiliares.Usuario;
import com.example.diego.rotas.banco.DBController;

import java.util.List;

public class GerenciarCadastro extends AppCompatActivity {

    private Button btListar;
    private String[] users;
    private ListView listarUsers;
    private DBController dbController;
    private Cursor cursor;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_cadastro);
        btListar = (Button) findViewById(R.id.buttonListar);
        listarUsers = (ListView) findViewById(R.id.ListUsers);
        dbController = new DBController(getBaseContext());
        users = new String[]{};


        btListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor = dbController.listarUsuarios();
                do{
                    users[0] = cursor.getString(cursor.getColumnIndex("login"));
                }while(cursor.moveToNext());
                adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, users);
                listarUsers.setAdapter(adapter);


            }
        });
    }
}
