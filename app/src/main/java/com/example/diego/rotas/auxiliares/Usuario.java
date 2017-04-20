package com.example.diego.rotas.auxiliares;

/**
 * Created by DBlac on 19/04/2017.
 */

public class Usuario {
    private long id;
    private String login;
    private String senha;
    private char tipo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
}
