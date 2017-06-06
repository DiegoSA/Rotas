package com.example.diego.rotas.banco;

import android.support.annotation.NonNull;

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
        sqlBuilder.append("nome VARCHAR(20) NOT NULL, ");
        sqlBuilder.append("telefone VARCHAR(10) NOT NULL, ");
        sqlBuilder.append("telefone1 VARCHAR(10) ");
        sqlBuilder.append("); ");

        return sqlBuilder.toString();
    }

    public static String createMotorista(){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE MOTORISTA( ");
        sqlBuilder.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("codigo INTEGER NOT NULL UNIQUE, ");
        sqlBuilder.append("nome VARCHAR(20) NOT NULL, ");
        sqlBuilder.append("telefone VARCHAR(10) NOT NULL, ");
        sqlBuilder.append("telefone1 VARCHAR(10) ");
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
        sqlBuilder.append("telefone VARCHAR(10) NOT NULL, ");
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

    public static String createCarregamento(){
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("CREATE TABLE CARREGAMENTO( ");
        sqlBuilder.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("numeroCarregamento INTEGER NOT NULL UNIQUE, ");
        sqlBuilder.append("dataFaturamento DATETIME NOT NULL, ");
        sqlBuilder.append("dataEntrega DATETIME, ");
        sqlBuilder.append("codigoMotorista INTEGER NOT NULL, ");
        sqlBuilder.append("codigoVeiculo INTEGER NOT NULL, ");
        sqlBuilder.append("quantEntregas INTEGER NOT NULL, ");
        sqlBuilder.append("FOREIGN KEY (codigoMotorista) REFERENCES MOTORISTA(codigo), ");
        sqlBuilder.append("FOREIGN KEY (codigoVeiculo) REFERENCES VEICULO(codigo) ");
        sqlBuilder.append("); ");

        return sqlBuilder.toString();
    }

    @NonNull
    public static String createPedido(){
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("CREATE TABLE PEDIDO( ");
        sqlBuilder.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("numeroNota INTEGER NOT NULL UNIQUE, ");
        sqlBuilder.append("codigoCliente INTEGER NOT NULL, ");
        sqlBuilder.append("dataFaturamento DATETIME NOT NULL, ");
        sqlBuilder.append("dataEntrega DATETIME, ");
        //sqlBuilder.append("valor NUMERIC (10, 2) NOT NULL, ");
        sqlBuilder.append("numeroCarregamento INTEGER NOT NULL, ");
        sqlBuilder.append("codigoVendedor INTEGER NOT NULL, ");
        sqlBuilder.append("FOREIGN KEY (codigoVendedor) REFERENCES VENDEDOR(codigo), ");
        sqlBuilder.append("FOREIGN KEY (codigoCliente) REFERENCES CLIENTE(codigo), ");
        sqlBuilder.append("FOREIGN KEY (numeroCarregamento) REFERENCES CARREGAMENTO(numeroCarregamento) ");
        sqlBuilder.append("); ");

        return sqlBuilder.toString();
    }

    public static void atualizarDados(){
        //criar scripts para atualizar as informações do app
    }
}
