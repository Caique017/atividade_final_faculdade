package com.estudos.java.model;

import com.estudos.java.exception.DocumentoInvalidoException;

public class PessoaJuridica extends Cliente {

    private String cnpj;

    public PessoaJuridica(int id, String nome, String cnpj, String contato) throws DocumentoInvalidoException {
        super(id, nome, contato);
        setCnpj(cnpj);
    }

    @Override
    public String getTipo() {
        return "Pessoa Jurídica";
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("   CNPJ: " + cnpj);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) throws DocumentoInvalidoException {
        String numeros = cnpj.replace(".", "").replace("/", "").replace("-", "");
        if (numeros.length() != 14 || !numeros.matches("[0-9]+")) {
            throw new DocumentoInvalidoException("CNPJ inválido: " + cnpj + ". O CNPJ deve ter 14 números.");
        }
        this.cnpj = numeros;
    }
}
