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
    private static final int DBVersion = 1;

    public DBHelper(Context context){

        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ScriptSQL.createUsuarios());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
}
