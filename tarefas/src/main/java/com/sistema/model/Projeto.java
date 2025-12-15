package com.sistema.model;

public class Projeto extends Base {

    private String nome;
    private String descricao;
    private int usuarioId;

    public Projeto() {
    }

    public Projeto(int id, String nome, String descricao, int usuarioId) {
        super(id);
        this.nome = nome;
        this.descricao = descricao;
        this.usuarioId = usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
