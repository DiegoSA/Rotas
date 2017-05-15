package com.example.diego.rotas.telas;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.diego.rotas.R;
import com.example.diego.rotas.banco.DBController;

public class GerenciarCadastro extends AppCompatActivity {

    private Button btListar;
    private String[] users;
    private ListView listarUsers;
    private DBController dbController;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;
    private AlertDialog alert;

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
                listarUsers.setAdapter(fullListView(cursor));
            }
        });

        listarUsers.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                cursor.moveToPosition(position);

                opcoesCadastro(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));

            }
        });
    }

    //tentando fazer funcionar o AlertDialog
    public void opcoesCadastro(final int position){
        alert = new AlertDialog.Builder(this)
                .setTitle("Alterar ou Remover?")
                .setPositiveButton("Alterar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String codigo;
                        codigo = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
                        Intent i = new Intent(getBaseContext(), AlterarUsuario.class);
                        i.putExtra("codigo", codigo);
                        startActivity(i);
                    }
                }).setNegativeButton("Remover", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbController.removeUser(position);
                    }
                })
                .show();

    }

    //preencher o ListView de Usuários
    public SimpleCursorAdapter fullListView(Cursor cursor){
        String[] columns = new String[]{"login", "tipo"};
        int[] to = new int[]{R.id.user, R.id.tipo};

        adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.list_usuarios, cursor, columns, to, 0);
        return adapter;
    }
}
