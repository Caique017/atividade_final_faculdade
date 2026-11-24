package com.estudos.java.model;

import com.estudos.java.exception.AnoInvalidoException;

public class Utilitario extends Veiculo {

    private int capacidadeCarga;

    public Utilitario(int id, String modelo, String fabricante, int ano, int capacidadeCarga) throws AnoInvalidoException {
        super(id, modelo, fabricante, ano);
        this.capacidadeCarga = capacidadeCarga;
    }

    @Override
    public String getTipo() {
        return "Utilitário";
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("   Capacidade de carga: " + capacidadeCarga + " kg");
    }

    public int getCapacidadeCarga() {
        return capacidadeCarga;
    }

    public void setCapacidadeCarga(int capacidadeCarga) {
        this.capacidadeCarga = capacidadeCarga;
    }
}
