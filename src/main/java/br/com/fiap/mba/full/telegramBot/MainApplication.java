package br.com.fiap.mba.full.telegramBot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class,args);
		new TratadorMensagemTelegramBot(
				new TelegramBot("897746755:AAG3Kh_LpbGeeJLiorrn6cVx5I_d6UQefJU")
		);
	}
}