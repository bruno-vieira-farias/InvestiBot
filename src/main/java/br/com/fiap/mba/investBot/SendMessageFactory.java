package br.com.fiap.mba.investBot;

import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *  Classe responsável por criar as instancias do SendMessage.
 */
@Component
public class SendMessageFactory {

    public SendMessage cria(long chatId, String mensagem, List<String[]> keyboardLayout) {
        SendMessage sendMessage =  new SendMessage(chatId, mensagem);

        if (keyboardLayout.size() != 0) {
            sendMessage.replyMarkup(new ReplyKeyboardMarkup(keyboardLayout.toArray(new String[0][0])));
        }

        return sendMessage;
    }
}