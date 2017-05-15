package com.example.diego.rotas.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.diego.rotas.auxiliares.Usuario;

/**
 * Created by diego on 4/13/17.
 */

public class DBController {
    private SQLiteDatabase db;
    private DBHelper banco;

    public DBController(Context context){
        banco = new DBHelper(context);
    }

    public String insertUser(Context context, Usuario usuario){
        ContentValues values;
        long result;

        values = new ContentValues();
        values.put("login", usuario.getLogin());
        values.put("senha", usuario.getSenha());
        values.put("tipo", String.valueOf(usuario.getTipo()));

        db = banco.getWritableDatabase();

        result = db.insertOrThrow("USUARIOS", null, values);

        db.close();
        if(result != -1){

            return "Usuario gravado com sucesso";

        }else {

            return "Erro ao inserir Usuário";

        }
    }

    public Cursor listarUsuarios(){

        String[] campos = {"_id", "login", "senha", "tipo"};

        db = banco.getReadableDatabase();

        Cursor cursor = db.query("USUARIOS", campos, null, null, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }

    public Cursor listarPedidos(){

        String[] campos = {"_id", "numeroPedido", "codigoCliente"};

        db = banco.getReadableDatabase();

        Cursor cursor = db.query("PEDIDO", campos, null, null, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }

    public void removeUser(int id){
        String where = "_id = " + id + ";";

        db = banco.getWritableDatabase();
        db.delete("USUARIOS", where, null);
        db.close();
    }

    public Cursor consultaUsuario(int id){

        String[] campos = {"_id", "login", "senha", "tipo"};

        db = banco.getReadableDatabase();

        Cursor cursor = db.query("USUARIOS", campos, null, null, null, null, null, null);
        cursor.moveToPosition(id);

        /*Usuario usuario = new Usuario();
        usuario.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
        usuario.setLogin(cursor.getString(cursor.getColumnIndexOrThrow("login")));
        usuario.setSenha(cursor.getString(cursor.getColumnIndexOrThrow("senha")));
        if(cursor.getString(cursor.getColumnIndexOrThrow("tipo")).toString().equals('A')){
            usuario.setTipo('A');
        }else if(cursor.getString(cursor.getColumnIndexOrThrow("tipo")).toString().equals('V')) {
            usuario.setTipo('V');
        }else if(cursor.getString(cursor.getColumnIndexOrThrow("tipo")).toString().equals('C')){
            usuario.setTipo('C');
        }else {
            usuario.setTipo('M');
        }*/


        return cursor;
    }

    public void alterarUsuario(Usuario usuario){
        ContentValues values;
        String where = "_id = " + String.valueOf(usuario.getId()) + ";";

        db = banco.getWritableDatabase();

        values = new ContentValues();
        values.put("login", usuario.getLogin());
        values.put("senha", usuario.getSenha());
        values.put("tipo", String.valueOf(usuario.getTipo()));

        db.update("USUARIOS", values, where, null);
        db.close();

    }
}
