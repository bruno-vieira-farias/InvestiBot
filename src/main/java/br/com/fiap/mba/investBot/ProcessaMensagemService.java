package br.com.fiap.mba.investBot;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Serviço que trata as mensagens recebidas do usuario e produz o retorno adequado.
 */
@Service
public class ProcessaMensagemService {
    private TaxaSelicService taxaSelicService;
    private RentalibidadePoupancaService rentalibidadePoupancaService;
    private MensagemInicialService mensagemInicialService;

    public ProcessaMensagemService(TaxaSelicService taxaSelicService, RentalibidadePoupancaService rentalibidadePoupancaService, MensagemInicialService mensagemInicialService) {
        this.taxaSelicService = taxaSelicService;
        this.rentalibidadePoupancaService = rentalibidadePoupancaService;
        this.mensagemInicialService = mensagemInicialService;
    }

    public List<RespostaInvestBot> processaMensagem(String mensagemRecebida) {
        String textoResposta;

        switch (mensagemRecebida) {
            case "/start":
                return mensagemInicialService.geraConversaInicial();

            case "Taxa selic hoje":
                textoResposta = "A taxa selic hoje esta é de ${a}%";
                return Collections.singletonList(criaRespostaInvestBot(textoResposta, taxaSelicService.obtemTaxaSelicHoje()));

            case "Taxa selic acumulada nos últimos 30 dias":
                textoResposta = "A taxa acumulada nos últimos 30 dias é de ${a}%";
                return Collections.singletonList(criaRespostaInvestBot(textoResposta, taxaSelicService.obtemTaxaSelicUltimosTrintaDias()));

            case "Rendimento da poupanca nos últimos 30 dias":
                textoResposta = "O rendimento da poupanca nos últimos 30 dias é de ${a}%.";
                return Collections.singletonList(criaRespostaInvestBot(textoResposta, rentalibidadePoupancaService.obtemRendimentoPoupancaUltimosTrintaDias()));

            case "Rendimento da poupança acumulado nos últimos 12 meses":
                textoResposta = "O rendimento da poupança acumulado nos últimos 12 meses é de ${a}%.";
                return Collections.singletonList(criaRespostaInvestBot(textoResposta, rentalibidadePoupancaService.obtemRendimentoPoupancaUltimosDozeMeses()));

            default:
                textoResposta = "desculpe, não entendi.";
                return Collections.singletonList(new RespostaInvestBot(textoResposta));
        }
    }

    private RespostaInvestBot criaRespostaInvestBot(String textoResposta, BigDecimal taxaCalculada) {
        return new RespostaInvestBot(textoResposta.replace("${a}", taxaCalculada.toString()));
    }
}