package br.com.fiap.mba.investBot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço que representa o InvestBot.
 *
 * O InvestBot interage com o usuário pela interface do BOT Telegram.
 */
@Service
public class InvestBotService {
    private TelegramBot bot;
    private ProcessaMensagemService processaMensagemService;
    private SendMessageFactory sendMessageFactory;

    public InvestBotService(TelegramBot bot, ProcessaMensagemService processaMensagemService, SendMessageFactory sendMessageFactory) {
        this.bot = bot;
        this.processaMensagemService = processaMensagemService;
        this.sendMessageFactory = sendMessageFactory;
    }

    /**
     *  Inicia o bot para interação com o usuário.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void startInvestBot() {
        bot.setUpdatesListener(updates -> {
            updates.forEach(
                    it -> processaMensagenRecebida(it.message().text(), it.message().chat().id())
            );
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    /**
     * Trata a mensagem recebida de acordo com as interações do usuário.
     *
     * @param mensagemRecebida é o input do usuario no bot.
     * @param chatId é um idenficador que representa uma chat ativo.
     */
    private void processaMensagenRecebida(String mensagemRecebida, long chatId) {
        bot.execute(new SendChatAction(chatId, ChatAction.typing.name()));
        List<RespostaInvestBot> respostas = processaMensagemService.processaMensagem(mensagemRecebida);
        responde(chatId, respostas);
    }

    /**
     * Responde a uma interação do usuário.
     *
     * @param chatId é um idenficador que representa uma chat ativo.
     * @param respostasInvestBot retorno enviado para o bot que será apresentado para o usuário.
     */
    private void responde(long chatId, List<RespostaInvestBot> respostasInvestBot) {
        SendMessage sendMessage;

        for (RespostaInvestBot resposta : respostasInvestBot) {
            sendMessage = sendMessageFactory.cria(chatId, resposta.getMensagem(), resposta.getKeyboardLayout());
            bot.execute(sendMessage);
        }
    }
}