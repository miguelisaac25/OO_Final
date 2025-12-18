package com.sistema.model;

public class Usuario extends Base {

    private String email;
    private String senha;

    public Usuario() {
    }

    public Usuario(int id, String email, String senha) {
        super(id);
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
