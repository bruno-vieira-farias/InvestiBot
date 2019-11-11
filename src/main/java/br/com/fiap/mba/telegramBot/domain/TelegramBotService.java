package br.com.fiap.mba.telegramBot.domain;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class TelegramBotService {
    private TelegramBot bot;
    private TelegramBotListener telegramBotListener;

    @Autowired
    public TelegramBotService(TelegramBot bot, TelegramBotListener telegramBotListener) {
        this.bot = bot;
        this.telegramBotListener = telegramBotListener;
    }

//    @EventListener(ApplicationReadyEvent.class)
    public void iniciaListener() { bot.setUpdatesListener(telegramBotListener);}
}