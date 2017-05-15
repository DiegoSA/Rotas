package com.example.diego.rotas.telas;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.diego.rotas.R;
import com.example.diego.rotas.auxiliares.Usuario;
import com.example.diego.rotas.banco.DBController;

import java.util.ArrayList;
import java.util.List;

public class AlterarUsuario extends AppCompatActivity {

    private Spinner tipo;
    private Button alter;
    private EditText login;
    private EditText senha;
    private Usuario usuario;
    private DBController dbController;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_usuario);

        dbController = new DBController(getBaseContext());

        login = (EditText) findViewById(R.id.editTextLogin);
        senha = (EditText) findViewById(R.id.editTextSenha);
        alter = (Button) findViewById(R.id.buttonAlter);
        gerarSpinner();

        Intent i = getIntent();
        int id = Integer.valueOf(i.getStringExtra("codigo"));

        cursor = dbController.consultaUsuario(id);

        login.setText(usuario.getLogin());
        senha.setText(usuario.getSenha());
        if(usuario.getTipo()=='A'){
            tipo.setSelection(0);
        }else if(usuario.getTipo()=='V'){
            tipo.setSelection(1);
        }else if(usuario.getTipo()=='C'){
            tipo.setSelection(2);
        }else {
            tipo.setSelection(3);
        }

        alter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.setLogin(login.getText().toString());
                usuario.setSenha(senha.getText().toString());
                if(String.valueOf(tipo.getSelectedItem()).equals("Administrador")){
                    usuario.setTipo('A');
                }else if(String.valueOf(tipo.getSelectedItem()).equals("Vendedor")) {
                    usuario.setTipo('V');
                }else if(String.valueOf(tipo.getSelectedItem()).equals("Cliente")){
                    usuario.setTipo('C');
                }else {
                    usuario.setTipo('M');
                }

                dbController.alterarUsuario(usuario);
            }
        });

        //Toast.makeText(getBaseContext(), id, Toast.LENGTH_SHORT).show();
    }

    public void gerarSpinner(){
        tipo = (Spinner) findViewById(R.id.spinnerTipo);
        List<String> list = new ArrayList<String>();
        list.add("Administrador");
        list.add("Vendedor");
        list.add("Cliente");
        list.add("Motorista");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipo.setAdapter(adapter);
    }
}
