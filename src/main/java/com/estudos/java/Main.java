// INTEGRANTES DO GRUPO
// CAIQUE NUNES OLIVEIRA - 2223200242 LÍDER
// JOÃO PAULO TELES DE SOUZA - 2223200206

package com.estudos.java;

import com.estudos.java.exception.AnoInvalidoException;
import com.estudos.java.exception.DocumentoInvalidoException;
import com.estudos.java.exception.NaoEncontradoException;
import com.estudos.java.exception.VeiculoVendidoException;
import com.estudos.java.model.Carro;
import com.estudos.java.model.Cliente;
import com.estudos.java.model.Moto;
import com.estudos.java.model.PessoaFisica;
import com.estudos.java.model.PessoaJuridica;
import com.estudos.java.model.Utilitario;
import com.estudos.java.model.Veiculo;
import com.estudos.java.model.Venda;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static List<Veiculo> veiculos = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Venda> vendas = new ArrayList<>();
    private static int proximoIdVeiculo = 1;
    private static int proximoIdCliente = 1;

    public static void main(String[] args) {
        int opcao = 0;

        while (opcao != 11) {
            exibirMenu();
            opcao = lerInt("Escolha uma opção: ");

            try {
                switch (opcao) {
                    case 1:
                        cadastrarVeiculo();
                        break;
                    case 2:
                        cadastrarCliente();
                        break;
                    case 3:
                        editarVeiculo();
                        break;
                    case 4:
                        editarCliente();
                        break;
                    case 5:
                        deletarVeiculo();
                        break;
                    case 6:
                        deletarCliente();
                        break;
                    case 7:
                        listarVeiculos();
                        break;
                    case 8:
                        listarClientes();
                        break;
                    case 9:
                        realizarVenda();
                        break;
                    case 10:
                        verHistoricoVendas();
                        break;
                    case 11:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (AnoInvalidoException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (DocumentoInvalidoException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (NaoEncontradoException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (VeiculoVendidoException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n===== CONCESSIONÁRIA =====");
        System.out.println("1 - Cadastrar Veículo");
        System.out.println("2 - Cadastrar Cliente (Pessoa Física / Jurídica)");
        System.out.println("3 - Editar Veículo");
        System.out.println("4 - Editar Cliente");
        System.out.println("5 - Deletar Veículo");
        System.out.println("6 - Deletar Cliente");
        System.out.println("7 - Listar Veículos");
        System.out.println("8 - Listar Clientes");
        System.out.println("9 - Realizar Venda");
        System.out.println("10 - Ver Histórico de Vendas");
        System.out.println("11 - Sair");
    }

    private static void cadastrarVeiculo() throws AnoInvalidoException {
        int tipo = lerInt("Tipo (1 - Carro, 2 - Moto, 3 - Utilitário): ");
        if (tipo < 1 || tipo > 3) {
            System.out.println("Tipo inválido!");
            return;
        }

        String modelo = lerTexto("Modelo: ");
        String fabricante = lerTexto("Fabricante: ");

        Veiculo veiculo;
        if (tipo == 1) {
            int portas = lerInt("Número de portas: ");
            int ano = lerInt("Ano de fabricação: ");
            veiculo = new Carro(proximoIdVeiculo, modelo, fabricante, ano, portas);
        } else if (tipo == 2) {
            int cilindradas = lerInt("Cilindradas: ");
            int ano = lerInt("Ano de fabricação: ");
            veiculo = new Moto(proximoIdVeiculo, modelo, fabricante, ano, cilindradas);
        } else {
            int carga = lerInt("Capacidade de carga (kg): ");
            int ano = lerInt("Ano de fabricação: ");
            veiculo = new Utilitario(proximoIdVeiculo, modelo, fabricante, ano, carga);
        }

        veiculos.add(veiculo);
        proximoIdVeiculo++;
        System.out.println("Veículo cadastrado com sucesso!");
    }

    private static void cadastrarCliente() throws DocumentoInvalidoException {
        int tipo = lerInt("Tipo (1 - Pessoa Física, 2 - Pessoa Jurídica): ");
        if (tipo < 1 || tipo > 2) {
            System.out.println("Tipo inválido!");
            return;
        }

        String nome = lerTexto("Nome: ");
        String contato = lerTexto("Contato: ");

        Cliente cliente;
        if (tipo == 1) {
            String cpf = lerTexto("CPF: ");
            cliente = new PessoaFisica(proximoIdCliente, nome, cpf, contato);
        } else {
            String cnpj = lerTexto("CNPJ: ");
            cliente = new PessoaJuridica(proximoIdCliente, nome, cnpj, contato);
        }

        clientes.add(cliente);
        proximoIdCliente++;
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void editarVeiculo() throws NaoEncontradoException, AnoInvalidoException {
        Veiculo veiculo = buscarVeiculo(lerInt("ID do veículo: "));
        veiculo.exibirInformacoes();

        veiculo.setAnoFabricacao(lerInt("Novo ano de fabricação: "));
        veiculo.setModelo(lerTexto("Novo modelo: "));
        veiculo.setFabricante(lerTexto("Novo fabricante: "));

        if (veiculo instanceof Carro) {
            Carro carro = (Carro) veiculo;
            carro.setPortas(lerInt("Novo número de portas: "));
        } else if (veiculo instanceof Moto) {
            Moto moto = (Moto) veiculo;
            moto.setCilindradas(lerInt("Novas cilindradas: "));
        } else if (veiculo instanceof Utilitario) {
            Utilitario utilitario = (Utilitario) veiculo;
            utilitario.setCapacidadeCarga(lerInt("Nova capacidade de carga (kg): "));
        }

        System.out.println("Veículo atualizado com sucesso!");
    }

    private static void editarCliente() throws NaoEncontradoException, DocumentoInvalidoException {
        Cliente cliente = buscarCliente(lerInt("ID do cliente: "));
        cliente.exibirInformacoes();

        if (cliente instanceof PessoaFisica) {
            PessoaFisica pessoaFisica = (PessoaFisica) cliente;
            pessoaFisica.setCpf(lerTexto("Novo CPF: "));
        } else if (cliente instanceof PessoaJuridica) {
            PessoaJuridica pessoaJuridica = (PessoaJuridica) cliente;
            pessoaJuridica.setCnpj(lerTexto("Novo CNPJ: "));
        }
        cliente.setNome(lerTexto("Novo nome: "));
        cliente.setContato(lerTexto("Novo contato: "));

        System.out.println("Cliente atualizado com sucesso!");
    }

    private static void deletarVeiculo() throws NaoEncontradoException {
        Veiculo veiculo = buscarVeiculo(lerInt("ID do veículo: "));
        veiculos.remove(veiculo);
        System.out.println("Veículo removido com sucesso!");
    }

    private static void deletarCliente() throws NaoEncontradoException {
        Cliente cliente = buscarCliente(lerInt("ID do cliente: "));
        clientes.remove(cliente);
        System.out.println("Cliente removido com sucesso!");
    }

    private static void listarVeiculos() {
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
            return;
        }
        for (Veiculo veiculo : veiculos) {
            veiculo.exibirInformacoes();
        }
    }

    private static void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        for (Cliente cliente : clientes) {
            cliente.exibirInformacoes();
        }
    }

    private static void realizarVenda() throws NaoEncontradoException, VeiculoVendidoException {
        Veiculo veiculo = buscarVeiculo(lerInt("ID do veículo: "));
        Cliente cliente = buscarCliente(lerInt("ID do cliente: "));

        veiculo.vender(cliente);
        Venda venda = new Venda(veiculo, cliente, veiculo.getDataVenda());
        vendas.add(venda);
        System.out.println("Venda realizada com sucesso!");
    }

    private static void verHistoricoVendas() {
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda realizada.");
            return;
        }
        for (Venda venda : vendas) {
            venda.exibirInformacoes();
        }
    }

    private static Veiculo buscarVeiculo(int id) throws NaoEncontradoException {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getId() == id) {
                return veiculo;
            }
        }
        throw new NaoEncontradoException("Veículo com ID " + id + " não encontrado.");
    }

    private static Cliente buscarCliente(int id) throws NaoEncontradoException {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        throw new NaoEncontradoException("Cliente com ID " + id + " não encontrado.");
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    private static int lerInt(String mensagem) {
        while (true) {
            try {
                return Integer.parseInt(lerTexto(mensagem));
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido!");
            }
        }
    }
}
