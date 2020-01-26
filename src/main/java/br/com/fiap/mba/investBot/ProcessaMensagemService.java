package br.com.fiap.mba.investBot;

import br.com.fiap.mba.hgfinanceClient.HgFinanceApiClient;
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
    private boolean ativarFluxoRendaVariavel = false;

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

        if(!ativarFluxoRendaVariavel)
            switch (mensagemRecebida) {
                case "/start":
                    return mensagemInicial();
                case "Taxa selic hoje":
                    return formataMensagemRetorno(1, taxaSelicService.obtemTaxaSelicDia());
                case "Taxa selic acumulada nos últimos 30 dias":
                    return formataMensagemRetorno(2, taxaSelicService.obtemTaxaSelicUltimosTrintaDias());
                case "Rendimento da poupanca nos últimos 30 dias":
                    return formataMensagemRetorno(3, rentalibidadePoupancaService.obtemRendimentoPoupancaUltimosTrintaDias());
                case "Rendimento da poupança acumulado nos últimos 12 meses":
                    return formataMensagemRetorno(4, rentalibidadePoupancaService.obtemRendimentoPoupancaUltimosDozeMeses());
                case "Quero saber sobre ações":
                    ativarFluxoRendaVariavel = true;
                    return RendaVariavelRetorno();
                default:
                    return padrao;
            }
        else {
            String ticker = mensagemRecebida;
            //Obter dados do ticker usando api hgfinance
            String retorno = HgFinanceApiClient.ObterDadosTicker(ticker);
            List<SendMessage> messages = new LinkedList<>();
            messages.add(new SendMessage(this.chatId, "O preço atual de ${a} é: ${b}".replace("${a}",ticker).replace("${b}",retorno)));

            return messages;
        }
    }

    private List<SendMessage> formataMensagemRetorno(Integer indiceResposta, BigDecimal taxaCalculada) {
        List<SendMessage> messages = new LinkedList<>();
        messages.add(new SendMessage(this.chatId, respostas.get(indiceResposta).replace("${a}", taxaCalculada.toString())));

        return messages;
    }

    private List<SendMessage> mensagemInicial() {
        List<SendMessage> messages = new LinkedList<>();


        messages.add(new SendMessage(this.chatId,"Oi, eu sou o InvestBot e posso te ajudar com valiosas informações de investimento em renda fixa.\n"));

        Keyboard replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new String[]{"Taxa selic hoje", "Taxa selic acumulada nos últimos 30 dias"},
                new String[]{"Rendimento da poupança nos últimos 30 dias", "Rendimento da poupança nos últimos 12 meses"},
                new String[]{"Quero saber sobre ações"}
        );

        messages.add(new SendMessage(this.chatId, "Selecione uma das opcões do menu e deixe o trabalho duro comigo.\n").replyMarkup(replyKeyboardMarkup));

        return messages;
    }

    private List<SendMessage> RendaVariavelRetorno(){
        List<SendMessage> messages = new LinkedList<>();
        messages.add(new SendMessage(this.chatId,"Informe o ticker da ação que deseja obter informações (EX: ITSA4, OIBR3, ABEV3): \n"));
        return messages;
    }
}