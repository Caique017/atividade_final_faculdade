package com.estudos.java.model;

import com.estudos.java.exception.AnoInvalidoException;
import com.estudos.java.exception.VeiculoVendidoException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Veiculo implements Exibivel {

    private int id;
    private String modelo;
    private String fabricante;
    private int anoFabricacao;
    private boolean vendido;
    private Cliente comprador;
    private LocalDate dataVenda;

    public Veiculo(int id, String modelo, String fabricante, int anoFabricacao) throws AnoInvalidoException {
        setAnoFabricacao(anoFabricacao);
        this.id = id;
        this.modelo = modelo;
        this.fabricante = fabricante;
    }

    public abstract String getTipo();

    @Override
    public void exibirInformacoes() {
        System.out.println("ID " + id + " | " + getTipo() + " | " + fabricante + " " + modelo
                + " | Ano: " + anoFabricacao + " | Status: " + getStatus());
        if (vendido) {
            String data = dataVenda.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            System.out.println("   Comprador: " + comprador.getNome() + " | Data da venda: " + data);
        }
    }

    public void vender(Cliente cliente) throws VeiculoVendidoException {
        vender(cliente, LocalDate.now());
    }

    public void vender(Cliente cliente, LocalDate data) throws VeiculoVendidoException {
        if (vendido) {
            throw new VeiculoVendidoException("O veículo " + id + " já foi vendido.");
        }
        this.comprador = cliente;
        this.dataVenda = data;
        this.vendido = true;
    }

    public String getStatus() {
        if (vendido) {
            return "Vendido";
        }
        return "Disponível";
    }

    public int getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) throws AnoInvalidoException {
        if (anoFabricacao > 2027 || anoFabricacao < 1900) {
            throw new AnoInvalidoException("Ano de fabricação inválido: " + anoFabricacao + ". Não são aceitos anos acima de 2027 ou inferior a 1900.");
        }
        this.anoFabricacao = anoFabricacao;
    }

    public boolean isVendido() {
        return vendido;
    }

    public Cliente getComprador() {
        return comprador;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }
}
