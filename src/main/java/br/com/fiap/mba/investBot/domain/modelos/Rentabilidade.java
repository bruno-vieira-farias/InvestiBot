package br.com.fiap.mba.investBot.domain.modelos;

public class Rentabilidade {
    private double porcentagem;
    private double valor;

    public Rentabilidade(double porcentagem, double valor) {
        this.porcentagem = porcentagem;
        this.valor = valor;
    }
}
