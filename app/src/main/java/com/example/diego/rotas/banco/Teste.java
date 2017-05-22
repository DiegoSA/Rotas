package com.example.diego.rotas.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by DBlac on 21/05/2017.
 */

public class Teste {

    public static String inserirPedido(){
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("INSERT INTO PEDIDO ( ");
        sqlBuilder.append("id, ");
        sqlBuilder.append("numeroNota, ");
        sqlBuilder.append("codigoCliente, ");
        sqlBuilder.append("dataFaturamento, ");
        sqlBuilder.append("numeroCarregamento, ");
        sqlBuilder.append("codigoVendedor) ");
        sqlBuilder.append("VALUES ( ");
        sqlBuilder.append("1, ");
        sqlBuilder.append("16210799, ");
        sqlBuilder.append("3968, ");
        sqlBuilder.append("'19/05/2017', ");
        sqlBuilder.append("12925, ");
        sqlBuilder.append("3 ");
        sqlBuilder.append("); ");

        return sqlBuilder.toString();
    }

    public static String inserirPedido1(){
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("INSERT INTO PEDIDO ( ");
        sqlBuilder.append("id, ");
        sqlBuilder.append("numeroNota, ");
        sqlBuilder.append("codigoCliente, ");
        sqlBuilder.append("dataFaturamento, ");
        sqlBuilder.append("numeroCarregamento, ");
        sqlBuilder.append("codigoVendedor) ");
        sqlBuilder.append("VALUES ( ");
        sqlBuilder.append("2, ");
        sqlBuilder.append("57503221, ");
        sqlBuilder.append("4103, ");
        sqlBuilder.append("'19/05/2017', ");
        sqlBuilder.append("12925, ");
        sqlBuilder.append("3 ");
        sqlBuilder.append("); ");

        return sqlBuilder.toString();
    }

    public static String inserirPedido2(){
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("INSERT INTO PEDIDO ( ");
        sqlBuilder.append("id, ");
        sqlBuilder.append("numeroNota, ");
        sqlBuilder.append("codigoCliente, ");
        sqlBuilder.append("dataFaturamento, ");
        sqlBuilder.append("numeroCarregamento, ");
        sqlBuilder.append("codigoVendedor) ");
        sqlBuilder.append("VALUES ( ");
        sqlBuilder.append("3, ");
        sqlBuilder.append("52504401, ");
        sqlBuilder.append("672, ");
        sqlBuilder.append("'19/05/2017', ");
        sqlBuilder.append("12925, ");
        sqlBuilder.append("3 ");
        sqlBuilder.append("); ");

        return sqlBuilder.toString();
    }



    public static String inserirCliente(){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("INSERT INTO CLIENTE(codigo, razao, fantasia, cnpj, telefone, endereco, numero, bairro, cidade, cep, codigoVendedor) VALUES (672, 'VILA BRUNA COMERCIO DE ALIMENTOS LTDA', 'MINE KALZONE', '11.017.108/0001-03', '83-32456382', 'RUA MANOEL ARRUDA CAVALCANTI', '805', 'MANAIRA', 'JOAO PESSOA', 58038680', 52); ");
        sqlBuilder.append("INSERT INTO CLIENTE(codigo, razao, fantasia, cnpj, telefone, endereco, numero, bairro, cidade, cep, codigoVendedor) VALUES (3968, 'CABRAL E FARIAS LANCHONETE LTDA', 'SUAVE SABOR JP I', '25.238.531/0001-48', '999228616', 'RUA ANA CAVALCANTI DE ALBUQUERQUE TEIXEI', '420', 'MANGABEIRA', 'JOÃO PESSOA', 58057470', 16); ");
        sqlBuilder.append("INSERT INTO CLIENTE(codigo, razao, fantasia, cnpj, telefone, endereco, numero, bairro, cidade, cep, codigoVendedor) VALUES (4103, 'JOSE CARLOS SOARES DE SOUSA JUNIOR', 'DU BISTRO', '23.384.699/0001-36', '999990053', 'AVENIDA GENERAL EDSON RAMALHO - ATÉ 811/', '218', 'MANAÍRA', 'JOAO PESSOA', 58038100', 57); ");

        return sqlBuilder.toString();
    }

    public static String inserirVeiculo() {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("INSERT INTO VEICULO(codigo, nome, placa) VALUES(6, 'BROS 150CC OEU-1127', 'OEU1127'); ");
        sqlBuilder.append("INSERT INTO VEICULO(codigo, nome, placa) VALUES(8, 'SPRINTER 311', 'QFK9910'); ");
        sqlBuilder.append("INSERT INTO VEICULO(codigo, nome, placa) VALUES(2, 'FIORINO QFG-1780', 'QFG1780'); ");
        sqlBuilder.append("INSERT INTO VEICULO(codigo, nome, placa) VALUES(3, 'KOMBI NQI-0298', 'NQI0298'); ");
        sqlBuilder.append("INSERT INTO VEICULO(codigo, nome, placa) VALUES(4, 'CAMINHÃO VW OFH-5498', 'OFH5498'); ");
        sqlBuilder.append("INSERT INTO VEICULO(codigo, nome, placa) VALUES(5, 'DUCATO OGA-0136', 'OGA0136'); ");
        sqlBuilder.append("INSERT INTO VEICULO(codigo, nome, placa) VALUES(10, 'PLASTPEL', 'NQB1056'); ");
        sqlBuilder.append("INSERT INTO VEICULO(codigo, nome, placa) VALUES(9, 'CLIENTE', 'NPV6436'); ");
        sqlBuilder.append("INSERT INTO VEICULO(codigo, nome, placa) VALUES(1, 'PARAMETRIZAÇÃO VEICULO TESTE', 'PC 1234'); ");
        sqlBuilder.append("INSERT INTO VEICULO(codigo, nome, placa) VALUES(7, 'MASTER FURGAO', 'AXV0663'); ");
        sqlBuilder.append("INSERT INTO VEICULO(codigo, nome, placa) VALUES(11, 'VENDEDOR', 'OFC0898'); ");

        return sqlBuilder.toString();
    }

    public static String inserirMotorista() {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("INSERT INTO MOTORISTA(codigo, nome, telefone, telefone1) VALUES(8930, 'JOSENILDO', '833235-4869', '833235-4869'); ");
        sqlBuilder.append("INSERT INTO MOTORISTA(codigo, nome, telefone, telefone1) VALUES(8928, 'JOSENILDO', '833235-4869', '833235-4869'); ");
        sqlBuilder.append("INSERT INTO MOTORISTA(codigo, nome, telefone, telefone1) VALUES(8920, 'JOSENILDO', '833235-4869', '833235-4869'); ");
        sqlBuilder.append("INSERT INTO MOTORISTA(codigo, nome, telefone, telefone1) VALUES(8899, 'JOSENILDO', '833235-4869', '833235-4869'); ");
        sqlBuilder.append("INSERT INTO MOTORISTA(codigo, nome, telefone, telefone1) VALUES(8931, 'JOSENILDO', '833235-4869', '833235-4869'); ");
        sqlBuilder.append("INSERT INTO MOTORISTA(codigo, nome, telefone, telefone1) VALUES(50, 'JOSENILDO', '833235-4869', '833235-4869'); ");
        sqlBuilder.append("INSERT INTO MOTORISTA(codigo, nome, telefone, telefone1) VALUES(57, 'JOSENILDO', '83 98867-6013', '833235-4869'); ");

        return sqlBuilder.toString();
    }

    public static String inserirVendedor() {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("INSERT INTO VENDEDOR (codigo, nome, telefone, telefone1) VALUES (29, 'BAIANO', '83 8706-7852', '83 9835-6597'); ");
        sqlBuilder.append("INSERT INTO VENDEDOR (codigo, nome, telefone, telefone1) VALUES (55, 'RAFAEL ANGELO SOUZA', '83 98738-8383', '83 3212-9003'); ");
        sqlBuilder.append("INSERT INTO VENDEDOR (codigo, nome, telefone, telefone1) VALUES (63, 'EM ANALISE FINANCEIRA', '83 3235-4869', '83 3235-4869'); ");
        sqlBuilder.append("INSERT INTO VENDEDOR (codigo, nome, telefone, telefone1) VALUES (20, 'LEYLA HONORATO', '83 3235-4869', '83 3235-4869'); ");
        sqlBuilder.append("INSERT INTO VENDEDOR (codigo, nome, telefone, telefone1) VALUES (60, 'TAISE ANDRADE', '83 98830-3354', '83 3235-4869'); ");
        sqlBuilder.append("INSERT INTO VENDEDOR (codigo, nome, telefone, telefone1) VALUES (3, 'MULTIPEL', '83 3235-4869', '83 3235-4869'); ");
        sqlBuilder.append("INSERT INTO VENDEDOR (codigo, nome, telefone, telefone1) VALUES (6, 'TORRES', '83 8610-6118', '83 8842-9706'); ");
        sqlBuilder.append("INSERT INTO VENDEDOR (codigo, nome, telefone, telefone1) VALUES (9, 'GILIANE DANTAS', '83 3235-4869', '83 3235-4869'); ");
        sqlBuilder.append("INSERT INTO VENDEDOR (codigo, nome, telefone, telefone1) VALUES (39, 'SEM ATENDIMENO', '83 3235-4869', '83 3235-4869'); ");
        sqlBuilder.append("INSERT INTO VENDEDOR (codigo, nome, telefone, telefone1) VALUES (50, 'RAYANNY', '83 3235-4869', '83 3235-4869'); ");
        sqlBuilder.append("INSERT INTO VENDEDOR (codigo, nome, telefone, telefone1) VALUES (16, 'CLEBER MARTINS', '83 8825-1739', '83 8811-2672'); ");
        sqlBuilder.append("INSERT INTO VENDEDOR (codigo, nome, telefone, telefone1) VALUES (43, 'THIAGO DA COSTA DINIZ', '83 8818-7746', '83 3235-4869'); ");
        sqlBuilder.append("INSERT INTO VENDEDOR (codigo, nome, telefone, telefone1) VALUES (52, 'DIEGO CARDOZO', '83 3235-4869', '83 3235-4869'); ");
        sqlBuilder.append("INSERT INTO VENDEDOR (codigo, nome, telefone, telefone1) VALUES (57, 'LUCIO DE FREITAS LIMA JUNIOR', '83 99933-7543', '83 98862-7528'); ");
        sqlBuilder.append("INSERT INTO VENDEDOR (codigo, nome, telefone, telefone1) VALUES (62, 'ROTATIVIDADE', '83 3235-4869', '83 3235-4869'); ");

        return sqlBuilder.toString();
    }

    public static String inserirCarregamento(){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("INSERT INTO CARREGAMENTO (numeroCarregamento, dataFaturamento, codigoMotorista, codigoVeiculo, quantEntregas) VALUES (12925, '19/05/2017', 8920, 5, 3); ");

        return sqlBuilder.toString();
    }
}
