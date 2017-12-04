package com.note.dx.note.model;

import java.io.Serializable;

//Classe model para facilitar a modelagem de dados do sistema.
public class Nota implements Serializable{

    private Long id;
    private String titulo;
    private String conteudo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public String toString() {
        return getTitulo();
    }
}
