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


    public static String createDefaultUser(){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("INSERT INTO USUARIOS ( ");
        sqlBuilder.append("        tipo, ");
        sqlBuilder.append("        senha, ");
        sqlBuilder.append("login ");
        sqlBuilder.append(") ");
        sqlBuilder.append("VALUES ( ");
        sqlBuilder.append("'A', ");
        sqlBuilder.append("'admin', ");
        sqlBuilder.append("'admin' ");
        sqlBuilder.append("); ");

        return sqlBuilder.toString();
    }

    public static String createVendedor(){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE VENDEDOR( ");
        sqlBuilder.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("codigo INTEGER NOT NULL UNIQUE, ");
        sqlBuilder.append("nome VARCHAR(20) NOT NULL ");
        sqlBuilder.append("); ");

        return sqlBuilder.toString();
    }

    public static String createMotorista(){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE MOTORISTA( ");
        sqlBuilder.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("codigo INTEGER NOT NULL UNIQUE, ");
        sqlBuilder.append("nome VARCHAR(20) NOT NULL ");
        sqlBuilder.append("); ");

        return sqlBuilder.toString();
    }

    public static String createVeiculo(){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE VEICULO( ");
        sqlBuilder.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("codigo INTEGER NOT NULL UNIQUE, ");
        sqlBuilder.append("nome VARCHAR(20) NOT NULL, ");
        sqlBuilder.append("placa VARCHAR(7) NOT NULL ");
        sqlBuilder.append("); ");
        return sqlBuilder.toString();
    }

    public static String createCliente(){
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("CREATE TABLE CLIENTE( ");
        sqlBuilder.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("codigo INTEGER NOT NULL UNIQUE, ");
        sqlBuilder.append("razao VARCHAR(50) NOT NULL, ");
        sqlBuilder.append("fantasia VARCHAR(40), ");
        sqlBuilder.append("cnpj VARCHAR(18) NOT NULL UNIQUE, ");
        sqlBuilder.append("endereco VARCHAR(60) NOT NULL, ");
        sqlBuilder.append("numero INTEGER NOT NULL, ");
        sqlBuilder.append("bairro VARCHAR(40) NOT NULL, ");
        sqlBuilder.append("cidade VARCHAR(50) NOT NULL, ");
        sqlBuilder.append("cep VARCHAR(10) NOT NULL, ");
        sqlBuilder.append("codigoVendedor INTEGER NOT NULL, ");
        sqlBuilder.append("FOREIGN KEY (codigoVendedor) REFERENCES VENDEDOR(codigo) ");
        sqlBuilder.append("); ");

        return sqlBuilder.toString();
    }

    
}
