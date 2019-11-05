package br.com.fiap.mba.full.telegramBot;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
public class AppConfig {

    @Bean
    public TelegramBot telegramBot() {
        return new TelegramBot("897746755:AAG3Kh_LpbGeeJLiorrn6cVx5I_d6UQefJU");
    }
}