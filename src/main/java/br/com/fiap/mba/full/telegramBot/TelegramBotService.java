package br.com.fiap.mba.full.telegramBot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelegramBotService {
    private TelegramBot bot;
    private ProcessaMensagemService processaMensagem;

    @Autowired
    public TelegramBotService(TelegramBot bot, ProcessaMensagemService processaMensagem) {
        this.bot = bot;
        this.processaMensagem = processaMensagem;
        iniciaListener();
    }

    private void iniciaListener() {
        bot.setUpdatesListener(updates -> {
            updates.forEach(update ->ProcessaMensagensRecebidas(update));
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    private void ProcessaMensagensRecebidas(Update update) {
        String textoResposta = processaMensagem.processaMensagem(update.message().text());
        responde(new Resposta(textoResposta,update.message().chat().id()));
    }

    private void responde(Resposta resposta) {
        SendResponse response = bot.execute(new SendMessage(resposta.getChatId(), resposta.getTexto()));
    }
}