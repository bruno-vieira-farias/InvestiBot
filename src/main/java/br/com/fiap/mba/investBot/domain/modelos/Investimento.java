package br.com.fiap.mba.investBot.domain.modelos;

public abstract class Investimento {
//    protected Indexador indexador;
    protected double taxa;
    protected int duracaoEmMeses;
    protected double valor;

    public abstract Rentabilidade calcularRentabilidade();
}
