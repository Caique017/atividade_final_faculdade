package com.estudos.java.model;

import com.estudos.java.exception.AnoInvalidoException;

public class Moto extends Veiculo {

    private int cilindradas;

    public Moto(int id, String modelo, String fabricante, int ano, int cilindradas) throws AnoInvalidoException {
        super(id, modelo, fabricante, ano);
        this.cilindradas = cilindradas;
    }

    @Override
    public String getTipo() {
        return "Moto";
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("   Cilindradas: " + cilindradas + " cc");
    }

    public int getCilindradas() {
        return cilindradas;
    }

    public void setCilindradas(int cilindradas) {
        this.cilindradas = cilindradas;
    }
}
