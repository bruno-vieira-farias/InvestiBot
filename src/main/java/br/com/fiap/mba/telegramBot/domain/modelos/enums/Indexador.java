package br.com.fiap.mba.telegramBot.domain.modelos.enums;

public enum Indexador {
    Cdi("cdi"),
    Selic("selic");

    private String url;
    Indexador(String url) {
        this.url = String.format("${hgfinance.url}/Taxes?key=${hgFinanceToken}&fields=only_results,%s", url);

    }
}
