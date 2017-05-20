package com.example.diego.rotas.auxiliares;

import java.util.Date;

/**
 * Created by DBlac on 20/05/2017.
 */

public class Entrega {
    private int id;
    private int numeroPedido;
    private int codigoCliente;
    private Date dataFaturamento;
    private Date dataEntrega;
    private int numeroCarregamento;

    public Entrega(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Date getDataFaturamento() {
        return dataFaturamento;
    }

    public void setDataFaturamento(Date dataFaturamento) {
        this.dataFaturamento = dataFaturamento;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public int getNumeroCarregamento() {
        return numeroCarregamento;
    }

    public void setNumeroCarregamento(int numeroCarregamento) {
        this.numeroCarregamento = numeroCarregamento;
    }
}
