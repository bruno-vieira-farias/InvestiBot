package br.com.fiap.mba.telegramBot.domain;

import org.springframework.stereotype.Service;

@Service
public class ProcessaMensagemService {

    public String processaMensagem(String mensagem){
        if (mensagem.toLowerCase().equals("bruno")){
            return "lindo";
        }

        return "Feio que dói";
    }
}
