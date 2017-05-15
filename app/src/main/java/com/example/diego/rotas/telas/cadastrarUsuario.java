package com.example.diego.rotas.telas;

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

public class cadastrarUsuario extends AppCompatActivity {

    private Spinner tipo;
    private Usuario usuario;
    private Cursor cursor;
    public boolean operation;

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

                operation = true;
                usuario = new Usuario();
                EditText login = (EditText) findViewById(R.id.editTextLogin);
                EditText senha = (EditText) findViewById(R.id.editTextSenha);
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

                cursor = crud.listarUsuarios();

                do {
                    if(cursor.getString(cursor.getColumnIndexOrThrow("login")).equals(login.getText().toString())){
                        Toast.makeText(getBaseContext(), "Usuário já Existe!", Toast.LENGTH_SHORT).show();
                        login.setText(null);
                        senha.setText(null);
                        operation = false;
                        break;
                    }
                }while (cursor.moveToNext());

                if(operation){

                    if(usuario.getSenha().toString() != null){
                        String resultado = crud.insertUser(cadastrarUsuario.this, usuario);

                        if(resultado.equals("Usuario gravado com sucesso")) {
                            Toast.makeText(getBaseContext(), resultado, Toast.LENGTH_SHORT).show();
                            login.setText(null);
                            senha.setText(null);
                        }else {
                            Toast.makeText(getBaseContext(), resultado, Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getBaseContext(), "Informe uma senha válida", Toast.LENGTH_SHORT).show();
                    }
                }




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
