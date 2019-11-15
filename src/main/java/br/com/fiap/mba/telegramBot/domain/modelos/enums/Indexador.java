package br.com.fiap.mba.telegramBot.domain.modelos.enums;

public enum Indexador {

    Cdi("cdi"),
    Selic("selic");

    private String nome;
    private String url;

    Indexador(String nome) {
        this.nome = nome;
        this.url = "/taxes";
    }

    public String getNome() { return nome; }
    public String
    getUrl() { return url; }
}
