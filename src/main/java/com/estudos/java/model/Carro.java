package com.estudos.java.model;

import com.estudos.java.exception.AnoInvalidoException;

public class Carro extends Veiculo {

    private int portas;

    public Carro(int id, String modelo, String fabricante, int ano, int portas) throws AnoInvalidoException {
        super(id, modelo, fabricante, ano);
        this.portas = portas;
    }

    @Override
    public String getTipo() {
        return "Carro";
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("   Portas: " + portas);
    }

    public int getPortas() {
        return portas;
    }

    public void setPortas(int portas) {
        this.portas = portas;
    }
}
