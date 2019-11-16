package br.com.fiap.mba.investBot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  Serviço que represeta o InvestBot.
 *
 *  O InvestBot interage com o usuário pela interface de um BOT do Telegram.
 */
@Service
public class InvestBotService {
    private TelegramBot bot;
    private ProcessaMensagemService processaMensagemService;

    @Autowired
    public InvestBotService(TelegramBot bot, ProcessaMensagemService processaMensagemService) {
        this.bot = bot;
        this.processaMensagemService = processaMensagemService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startInvestBot() {
        bot.setUpdatesListener(updates -> {
            updates.forEach(
                    it -> processaMensagenRecebida(it.message().text(), it.message().chat().id())
            );
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    private void processaMensagenRecebida(String mensagemRecebida, long chatId) {
        bot.execute(new SendChatAction(chatId, ChatAction.typing.name()));
        List<SendMessage> respostas = processaMensagemService.processaMensagem(mensagemRecebida, chatId);
        responde(respostas);
    }

    private void responde( List<SendMessage> respostas) {
        respostas.forEach(sendMessage -> bot.execute(sendMessage));
    }
}