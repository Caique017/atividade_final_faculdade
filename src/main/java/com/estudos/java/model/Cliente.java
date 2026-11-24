package com.estudos.java.model;

public abstract class Cliente implements Exibivel {

    private int id;
    private String nome;
    private String contato;

    public Cliente(int id, String nome, String contato) {
        this.id = id;
        this.nome = nome;
        this.contato = contato;
    }

    public abstract String getTipo();

    @Override
    public void exibirInformacoes() {
        System.out.println("ID " + id + " | " + getTipo() + " | " + nome + " | Contato: " + contato);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}
