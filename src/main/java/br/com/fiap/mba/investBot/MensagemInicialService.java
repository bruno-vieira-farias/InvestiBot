package br.com.fiap.mba.investBot;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MensagemInicialService {

    public List<RespostaInvestBot> geraConversaInicial() {
        return Arrays.asList(
                new RespostaInvestBot("Oi, eu sou o InvestBot e posso te ajudar com valiosas informações de investimento."),
                new RespostaInvestBot("Selecione uma das opcões do menu e deixe o trabalho duro comigo.", criaLayoutBotoesMenu())
        );
    }

    private List<String[]> criaLayoutBotoesMenu() {
        return Arrays.asList(
                new String[]{"Taxa selic hoje", "Taxa selic acumulada nos últimos 30 dias"},
                new String[]{"Rendimento da poupanca nos últimos 30 dias", "Rendimento da poupança acumulado nos últimos 12 meses"}
        );
    }
}