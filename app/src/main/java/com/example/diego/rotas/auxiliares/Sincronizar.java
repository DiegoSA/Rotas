package com.example.diego.rotas.auxiliares;

/**
 * Created by DBlac on 20/05/2017.
 */

public class Sincronizar {

    public void consultarCarregamento(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("SELECT NUMCAR, DATAMON, CODMOTORISTA, CODVEICULO, NUMNOTAS ");
        stringBuilder.append("FROM PCCARREG ");
        stringBuilder.append("WHERE DTFECHA IS NULL ");
        stringBuilder.append("AND NUMCAR > 12924 ");
        stringBuilder.append("ORDER BY NUMCAR; ");
    }

    public void consultaCliente(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("SELECT CODCLI, CLIENTE, FANTASIA, CGCENT, ENDERCOM, NUMEROCOM, TELCOM, BAIRROCOM, MUNICCOM, CEPCOM, CODUSUR1 ");
        stringBuilder.append("FROM PCCLIENT ");
    }

    public void consultaEntrega(int numeroCarregamento){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("SELECT NUMPED, CODCLI, DATA, VLATEND, NUMCAR ");
        stringBuilder.append("FROM PCPEDC ");
        stringBuilder.append("WHERE NUMCAR = " + numeroCarregamento + " ");
    }

    public void consultaVendedor(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("SELECT CODUSUR, NOME, TELEFONE1, TELEFONE2 ");
        stringBuilder.append("FROM PCUSUARI ");
        stringBuilder.append("WHERE DTTERMINO IS NULL; ");
    }

    public void consultaMotorista(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("SELECT MATRICULA, NOME_GUERRA, CELULAR, FONE ");
        stringBuilder.append("FROM PCEMPR ");
        stringBuilder.append("WHERE CODSETOR = 9 ");
        stringBuilder.append("AND SITUACAO = 'A'; ");
    }

    public void consultaVeiculo(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("SELECT CODVEICULO, DESCRICAO, PLACA ");
        stringBuilder.append("FROM PCVEICUL ");
    }
}
