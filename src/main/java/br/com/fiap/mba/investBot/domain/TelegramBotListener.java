package br.com.fiap.mba.investBot.domain;

import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TelegramBotListener implements UpdatesListener {
    private ProcessaMensagemService processaMensagemService;
    private TelegramBotSendResponseService sendResponseService;

    public TelegramBotListener(ProcessaMensagemService processaMensagemService, TelegramBotSendResponseService sendResponseService) {
        this.processaMensagemService = processaMensagemService;
        this.sendResponseService = sendResponseService;
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update ->
                processaMensagenRecebida(update.message().text(), update.message().chat().id())
        );
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void processaMensagenRecebida(String mensagemRecebida, long chatId) {
        String mensagemResposta = processaMensagemService.processaMensagem(mensagemRecebida);
        sendResponseService.responde(mensagemResposta, chatId);
    }
}
