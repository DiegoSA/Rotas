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

}
