package br.com.fiap.mba.investBot;

import org.springframework.stereotype.Service;

/**
 * Serviço que é interface com o usuário do Telegram.
 */
@Service
public class ProcessaMensagemService {
    private TaxaSelicService taxaSelicService;

    public ProcessaMensagemService(TaxaSelicService taxaSelicService) {
        this.taxaSelicService = taxaSelicService;
    }

    public String processaMensagem(String mensagemRecebida){
        switch(mensagemRecebida) {
            case "/start":
                return mensagemInicial();
            case "1":
                return "A taxa selic diaria atual é " + taxaSelicService.obtemTaxaSelicDia() + "%";
            case "2":
                return "A taxa selic mensal  é " + taxaSelicService.obtemTaxaSelicUltimosTrintaDias() + "%";
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