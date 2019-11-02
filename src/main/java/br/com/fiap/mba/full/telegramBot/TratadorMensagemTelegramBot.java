package br.com.fiap.mba.full.telegramBot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

public class TratadorMensagemTelegramBot {
    private TelegramBot bot;

    public TratadorMensagemTelegramBot(TelegramBot bot) {
        this.bot = bot;
        start();
    }

    private void start(){
        bot.setUpdatesListener(updates -> {
            updates.forEach(update -> trataMensagemRecebida(update) );
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    private void trataMensagemRecebida(Update update) {
        printConsole(update);
        responde(update);
    }

    private void printConsole(Update update){
        System.out.println(update.message().text());
    }

    private void responde(Update update){
        long chatId = update.message().chat().id();
        SendResponse response = bot.execute(new SendMessage(chatId, "Não enche."));
    }
}