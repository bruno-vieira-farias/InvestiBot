package br.com.fiap.mba.full.demo;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		TelegramBot bot = new TelegramBot("897746755:AAG3Kh_LpbGeeJLiorrn6cVx5I_d6UQefJU");
		bot.setUpdatesListener(updates -> {
			updates.forEach(update -> trataMensagemRecebida(update,bot));
			return UpdatesListener.CONFIRMED_UPDATES_ALL;
		});
	}

	private static void trataMensagemRecebida(Update update,TelegramBot bot){
		System.out.println(update.message().text());
		responde(update, bot);
	}

	private static void responde(Update update,  TelegramBot bot){
		long chatId = update.message().chat().id();
		SendResponse response = bot.execute(new SendMessage(chatId, "Valew."));
	}
}