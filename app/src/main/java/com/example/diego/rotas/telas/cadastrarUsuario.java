package com.example.diego.rotas.telas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.diego.rotas.R;
import com.example.diego.rotas.banco.DBController;

import java.util.ArrayList;
import java.util.List;

public class cadastrarUsuario extends AppCompatActivity {

    private Spinner tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        gerarSpinner();

        final DBController crud = new DBController(getBaseContext());;
        Button cadastrar = (Button) findViewById(R.id.buttonCadastrar);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText login = (EditText) findViewById(R.id.editTextLogin);
                EditText senha = (EditText) findViewById(R.id.editTextSenha);
                String usuario = login.getText().toString();
                String password = senha.getText().toString();
                String type;
                if(String.valueOf(tipo.getSelectedItem()).equals("Administrador")){
                    type = "A";
                }else if(String.valueOf(tipo.getSelectedItem()).equals("Vendedor")) {
                    type = "V";
                }else if(String.valueOf(tipo.getSelectedItem()).equals("Cliente")){
                    type = "C";
                }else {
                    type = "M";
                }

                String resultado = crud.insertUser(usuario,password,type);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
            }
        });
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
