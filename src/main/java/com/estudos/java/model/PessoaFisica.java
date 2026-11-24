package com.estudos.java.model;

import com.estudos.java.exception.DocumentoInvalidoException;

public class PessoaFisica extends Cliente {

    private String cpf;

    public PessoaFisica(int id, String nome, String cpf, String contato) throws DocumentoInvalidoException {
        super(id, nome, contato);
        setCpf(cpf);
    }

    @Override
    public String getTipo() {
        return "Pessoa Física";
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("   CPF: " + cpf);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) throws DocumentoInvalidoException {
        String numeros = cpf.replace(".", "").replace("-", "");
        if (numeros.length() != 11 || !numeros.matches("[0-9]+")) {
            throw new DocumentoInvalidoException("CPF inválido: " + cpf + ". O CPF deve ter 11 números.");
        }
        this.cpf = numeros;
    }
}
