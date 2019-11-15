package br.com.fiap.mba.investBot;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Serviço que é interface com o usuário do Telegram.
 */
@Service
public class ProcessaMensagemService {
    private TaxaSelicService taxaSelicService;
    private HashMap<Integer, String> respostas = new HashMap<>();

    public ProcessaMensagemService(TaxaSelicService taxaSelicService) {
        this.taxaSelicService = taxaSelicService;
        respostas.put(1, "A taxa selic diaria atual é ${a}%");
        respostas.put(2, "A taxa selic mensal  é ${a}%");
        respostas.put(3, "Acho que ainda não sei fazer esta conta.");
        respostas.put(4, "Acho que ainda não sei fazer esta conta.");
        respostas.put(5, "Acho que faltei nesta aula.");
    }

    public String processaMensagem(String mensagemRecebida) {
        switch (mensagemRecebida) {
            case "/start":
                return mensagemInicial();
            case "1":
                return formataMensageRetorno(1, taxaSelicService.obtemTaxaSelicDia());
            case "2":
                return formataMensageRetorno(2, taxaSelicService.obtemTaxaSelicUltimosTrintaDias());
            case "3":
                return formataMensageRetorno(3, BigDecimal.ZERO);
            case "4":
                return formataMensageRetorno(4, BigDecimal.ZERO);
            case "5":
                return formataMensageRetorno(5, BigDecimal.ZERO);
            default:
                return "desculpe, não entendi.";
        }
    }

    private String formataMensageRetorno(Integer indiceResposta, BigDecimal taxaCalculada) {
        return respostas.get(indiceResposta).replace("${a}", taxaCalculada.toString());
    }

    private String mensagemInicial() {
        StringBuilder str = new StringBuilder();
        str.append("Oi, eu sou o InvestBot e posso te ajudar com valiosas informações de investimento.\n");
        str.append("Digite uma das opcões do menu e deixe o trabalho duro comigo.\n");
        str.append("1 - Taxa Selic Diaria.\n");
        str.append("2 - Taxa Selic Anual.\n");
        str.append("3 - Taxa Poupanca Mensal.\n");
        str.append("4 - Taxa poupanca Anual.\n");
        str.append("5 - Calcular o rendimento de um investimento.\n");

        return str.toString();
    }
}