package br.com.fiap.mba.telegramBot.domain;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
public class TelegramBotSendResponseService {
    private TelegramBot telegramBot;

    public TelegramBotSendResponseService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void responde(Resposta resposta) {
        telegramBot.execute(new SendMessage(resposta.getChatId(), resposta.getTexto()));
    }
}
