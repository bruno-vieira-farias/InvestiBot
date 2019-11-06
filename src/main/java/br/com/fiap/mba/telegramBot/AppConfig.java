package br.com.fiap.mba.telegramBot;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
public class AppConfig {

    @Bean
    public TelegramBot telegramBot(@Value("${telegram-bot.token}") String token) {
        return new TelegramBot(token);
    }
}