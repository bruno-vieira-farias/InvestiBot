package br.com.fiap.mba.investBot.domain;

import org.springframework.stereotype.Service;

@Service
public class ProcessaMensagemService {
    public String processaMensagem(String mensagemRecebida){
        switch(mensagemRecebida) {
            case "/start":
                return mensagemInicial();
            case "1":
                return "Aqui temos o texto ao pressionar 1";
            default:
                return "desculpe, não entendi.";
        }
    }

    private String mensagemInicial(){
        StringBuilder str = new StringBuilder();
        str.append("Oi, sou o investiBot e gostaria de te ajudar a poupar. Posso te ajudar com algumas informacoes, o que quer saber?\n");
        str.append("1 - Taxa Selic Diaria.\n");
        str.append("2 - Taxa Selic Anual.\n");
        str.append("3 - Taxa Poupanca Mensal.\n");
        str.append("4 - Taxa poupanca Anual.\n");
        str.append("5 - Calcular o rendimento de um investimento.\n");

        return str.toString();
    }
}
