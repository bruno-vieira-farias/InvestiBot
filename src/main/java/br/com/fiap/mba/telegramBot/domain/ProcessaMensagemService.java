package br.com.fiap.mba.telegramBot.domain;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessaMensagemService {

    public List<Resposta> processaMensagem(String mensagem, long chatId){
        List<Resposta> respostas = new ArrayList<>();
        if(mensagem.equals("/start")){
            respostas.add(responder("Olá", chatId));
            respostas.add(responder("Irei te ajudar com suas finanças e investimentos", chatId));
            respostas.add(responder("Você deseja informações sobre renda fixa ou variável?", chatId));
            return respostas;
        }
        else if(mensagem.toLowerCase().contains("fixa")){

            respostas.add(responder("Vamos calcular um investimento de renda fixa", chatId));
            respostas.add(responder("Esse tipo de investimento pode ser baseado no indice CDI (como CDBs, LCIs, LCAs, etc)"
                    + "ou SELIC (como o Tesouro SELIC)", chatId));
            respostas.add(responder("Qual investimento gostaria de simular?", chatId));
            return respostas;
        }

        respostas.add(responder("Não entendi...", chatId));
        return respostas;
    }

    public Resposta responder(String mensagem, long chatId){
        return new Resposta(mensagem, chatId);
    }

}
