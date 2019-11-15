package br.com.fiap.mba.investBot.domain;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
public class TelegramBotSendResponseService {
    private TelegramBot telegramBot;

    public TelegramBotSendResponseService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void responde(String mensagemResposta, Long chatId) {
        // telegramBot.execute(new SendChatAction(resposta.getChatId(), ChatAction.typing.name()));
        telegramBot.execute(new SendMessage(chatId, mensagemResposta));
    }
}
