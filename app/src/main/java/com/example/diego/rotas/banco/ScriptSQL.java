package com.example.diego.rotas.banco;

/**
 * Created by DBlac on 19/04/2017.
 */

public class ScriptSQL {

    public static String createUsuarios(){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS USUARIOS( ");
        sqlBuilder.append("_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("login VARCHAR(20) NOT NULL, ");
        sqlBuilder.append("senha VARCHAR(10) NOT NULL, ");
        sqlBuilder.append("tipo CHAR NOT NULL ");
        sqlBuilder.append("CHECK (tipo IN ('A', 'V', 'M', 'C')) ");
        sqlBuilder.append("); ");

        return sqlBuilder.toString();
    }


}
