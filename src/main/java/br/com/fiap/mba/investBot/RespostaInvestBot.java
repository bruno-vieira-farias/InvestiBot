package br.com.fiap.mba.investBot;

import java.util.ArrayList;
import java.util.List;

/**
 * Respresenta as respostas dadas pelo InvestiBot
 */
public class RespostaInvestBot {
    private String mensagem;
    private List<String[]> keyboardLayout = new ArrayList<>();

    public RespostaInvestBot(String mensagem) {
        this.mensagem = mensagem;
    }

    public RespostaInvestBot(String mensagem, List<String[]> keyboardLayout) {
        this.mensagem = mensagem;
        this.keyboardLayout = keyboardLayout;
    }

    public String getMensagem() {
        return mensagem;
    }

    public List<String[]> getKeyboardLayout() {
        return keyboardLayout;
    }

}