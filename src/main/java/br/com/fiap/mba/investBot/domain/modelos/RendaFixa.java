package br.com.fiap.mba.investBot.domain.modelos;

public class RendaFixa extends Investimento {
    private int porcentagemPosfixada;
    private int porcentagemPrefixada;

    @Override
    public Rentabilidade calcularRentabilidade() {
        return new Rentabilidade(10,10);
    }
}
