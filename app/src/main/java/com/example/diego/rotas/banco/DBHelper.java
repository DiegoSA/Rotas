package com.example.diego.rotas.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.StringBuilderPrinter;

/**
 * Created by diego on 4/12/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DBName = "ROTA";
    public static final String TABLE_USER = "Usuarios";
    public static final String login = "login";
    public static final String senha = "senha";
    public static final String tipo = "tipo";
    private static final int DBVersion = 1;

    public DBHelper(Context context){
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_USER + " ("
                + login + "TEXT,"
                + senha + "TEXT,"
                + tipo + "TEXT"
                + ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
}
