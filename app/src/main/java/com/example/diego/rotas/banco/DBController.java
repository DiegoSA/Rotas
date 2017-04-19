package com.example.diego.rotas.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

/**
 * Created by diego on 4/13/17.
 */

public class DBController {
    private SQLiteDatabase db;
    private DBHelper banco;

    public DBController(Context context){
        banco = new DBHelper(context);
    }

    public String insertUser(String login, String senha, String tipo){
        ContentValues values;
        long result;

        db = banco.getWritableDatabase();
        values = new ContentValues();
        values.put(DBHelper.login, login);
        values.put(DBHelper.senha, senha);
        values.put(DBHelper.tipo, tipo);



        //result = db.insert("Usuarios", null, values);
        String sql =  "INSERT INTO Usuarios (login, senha, tipo) VALUES ("
                + login + ", " + senha + ", " + tipo + ")";
        //inserir direto o codigo de inserir usuário.
        db.execSQL(sql);

        db.close();
        /*if(result == -1){

            return "Erro ao inserir Usuário";

        }else {

            return "Usuario gravado com sucesso";

        }*/
        return null;

    }

    public Cursor listarUsuarios(){
        Cursor cursor;
        String[] campos = {DBHelper.login};

        db = banco.getReadableDatabase();

        cursor = db.query(DBHelper.TABLE_USER, campos, null, null, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }

}
