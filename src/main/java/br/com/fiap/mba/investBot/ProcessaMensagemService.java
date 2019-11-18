package br.com.fiap.mba.investBot;

import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Serviço que é interface com o usuário do Telegram.
 */
@Service
public class ProcessaMensagemService {
    private TaxaSelicService taxaSelicService;
    private RentalibidadePoupancaService rentalibidadePoupancaService;
    private HashMap<Integer, String> respostas = new HashMap<>();
    private long chatId;
    public ProcessaMensagemService(TaxaSelicService taxaSelicService , RentalibidadePoupancaService rentalibidadePoupancaService) {
        this.taxaSelicService = taxaSelicService;
        this.rentalibidadePoupancaService = rentalibidadePoupancaService;
        respostas.put(1, "A taxa selic hoje esta é de ${a}%");
        respostas.put(2, "A taxa acumulada nos últimos 30 dias é de ${a}%");
        respostas.put(3, "O rendimento da poupanca nos últimos 30 dias é de ${a}%.");
        respostas.put(4, "O rendimento da poupança acumulado nos últimos 12 meses é de ${a}%.");
        respostas.put(5, "Acho que faltei nesta aula.");
    }

    public List<SendMessage> processaMensagem(String mensagemRecebida, long chatId) {
        this.chatId = chatId;

        List<SendMessage> padrao = new LinkedList<SendMessage>();
        padrao.add(new SendMessage(chatId,"desculpe, não entendi."));

        switch (mensagemRecebida) {
            case "/start":
                return mensagemInicial();
            case "Taxa selic hoje":
                return formataMensageRetorno(1, taxaSelicService.obtemTaxaSelicDia());
            case "Taxa selic acumulada nos últimos 30 dias":
                return formataMensageRetorno(2, taxaSelicService.obtemTaxaSelicUltimosTrintaDias());
            case "Rendimento da poupanca nos últimos 30 dias":
                return formataMensageRetorno(3, rentalibidadePoupancaService.obtemRendimentoPoupancaUltimosTrintaDias());
            case "Rendimento da poupança acumulado nos últimos 12 meses":
                return formataMensageRetorno(4, rentalibidadePoupancaService.obtemRendimentoPoupancaUltimosDozeMeses());
            case "5":
                return formataMensageRetorno(5, BigDecimal.ZERO);
            default:
                return padrao;
        }
    }

    private List<SendMessage> formataMensageRetorno(Integer indiceResposta, BigDecimal taxaCalculada) {
        List<SendMessage> messages = new LinkedList<>();
        messages.add(new SendMessage(this.chatId, respostas.get(indiceResposta).replace("${a}", taxaCalculada.toString())));

        return messages;
    }

    private List<SendMessage> mensagemInicial() {
        List<SendMessage> messages = new LinkedList<>();


        messages.add(new SendMessage(this.chatId,"Oi, eu sou o InvestBot e posso te ajudar com valiosas informações de investimento.\n"));

        Keyboard replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new String[]{"Taxa selic hoje", "Taxa selic acumulada nos últimos 30 dias"},
                new String[]{"Rendimento da poupanca nos últimos 30 dias", "Rendimento da poupança acumulado nos últimos 12 meses"}
        );

        messages.add(new SendMessage(this.chatId, "Selecione uma das opcões do menu e deixe o trabalho duro comigo.\n").replyMarkup(replyKeyboardMarkup));

        return messages;
    }
}