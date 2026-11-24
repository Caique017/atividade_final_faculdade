package com.estudos.java.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Venda implements Exibivel {

    private Veiculo veiculo;
    private Cliente cliente;
    private LocalDate data;

    public Venda(Veiculo veiculo, Cliente cliente, LocalDate data) {
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.data = data;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " | "
                + veiculo.getTipo() + " " + veiculo.getFabricante() + " " + veiculo.getModelo()
                + " | Cliente: " + cliente.getNome();
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getData() {
        return data;
    }
}
