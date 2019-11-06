package br.com.fiap.mba.telegramBot.domain;

public class Resposta {
    private String texto;
    private Long chatId;

    public Resposta(String texto, Long chatId) {
        this.texto = texto;
        this.chatId = chatId;
    }

    public String getTexto() {
        return texto;
    }

    public Long getChatId() {
        return chatId;
    }
}
