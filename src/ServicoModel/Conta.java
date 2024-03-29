package ServicoModel;

import UsuarioModel.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static View.Main.criarConta;

public class Conta {
    private static int contadorContas = 1;
    private int numeroConta;
    private Cliente cliente;
    private List<Produto> carrinho = new ArrayList<>();
    private double saldoCarrinho = 0.0;

    public Conta(int numeroConta, Cliente cliente) {
        this.numeroConta = contadorContas;
        this.cliente = cliente;
        contadorContas += 1;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public List<Produto> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(List<Produto> carrinho) {
        this.carrinho = carrinho;
    }

    public double getSaldoCarrinho() {
        return saldoCarrinho;
    }

    public void setSaldoCarrinho(double saldoCarrinho) {
        this.saldoCarrinho = saldoCarrinho;
    }

    public String toString() {
        return "\nNúmero da conta: " + this.getNumeroConta() +
                "\nNome: " + this.cliente.getNomeCompleto() +
                "\nCPF: " + this.cliente.getCpf() +
                "\nEmail: " + this.cliente.getEmailCorporativo() +
                "\nSaldo Carrinho: " + this.getSaldoCarrinho() +
                "\n";
    }

//    public void calcularCompra1(Plano plano ){
//        if (plano.getPreco() > 0) {
//            setSaldoCarrinho(getSaldoCarrinho() + plano.getPreco());
//            System.out.println("Produto adicionado ao carrinho");
//        } else {System.out.println("Não foi possível realizar a transterência");}
//
//    }

    public void calcularCompra(List<Produto> carrinho) {

        Optional<Plano> optional = carrinho.stream()
                .flatMap(produto -> produto.getPlanoPagamento().stream().filter(c -> c.getPrecoPlano() == 0))
                .findAny();
        if (optional.isPresent()) {
            System.out.println("Entre em contato conosco para fazer o seu orçamento");
        } else {
            carrinho.stream().flatMap(produto -> produto.getPlanoPagamento().stream().filter(c -> c.getPrecoPlano() > 0))
                    .forEach(c -> setSaldoCarrinho(c.getPrecoPlano() + saldoCarrinho));
            System.out.println("Valor total:" + saldoCarrinho);
        }

    }
}