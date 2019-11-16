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
    private RentalibidadePoupancaService rentalibidadePoupancaService;
    private HashMap<Integer, String> respostas = new HashMap<>();

    public ProcessaMensagemService(TaxaSelicService taxaSelicService , RentalibidadePoupancaService rentalibidadePoupancaService) {
        this.taxaSelicService = taxaSelicService;
        this.rentalibidadePoupancaService = rentalibidadePoupancaService;
        respostas.put(1, "A taxa selic hoje esta é de ${a}%");
        respostas.put(2, "A taxa acumulada nos últimos 30 dias é de ${a}%");
        respostas.put(3, "O rendimento da poupanca nos últimos 30 dias é de ${a}%.");
        respostas.put(4, "O rendimento da poupança acumulado nos últimos 12 meses é de ${a}%.");
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
                return formataMensageRetorno(3, rentalibidadePoupancaService.obtemRendimentoPoupancaUltimosTrintaDias());
            case "4":
                return formataMensageRetorno(4, rentalibidadePoupancaService.obtemRendimentoPoupancaUltimosDozeMeses());
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
        str.append("1 - Taxa selic hoje.\n");
        str.append("2 - Taxa selic acumulada nos últimos 30 dias.\n");
        str.append("3 - Rendimento da poupanca nos últimos 30 dias.\n");
        str.append("4 - Rendimento da poupança acumulado nos últimos 12 meses.\n");
        str.append("5 - Simule um investimento em renda fixa.\n");

        return str.toString();
    }
}