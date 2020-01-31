package br.com.fiap.mba.investBot;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Servico responsável por gerar as mensagens iniciais do bot.
 *
 * Existem botõess que facilitam a navegação entre as funcionalidades disponíveis.
 */
@Service
public class MensagemInicialService {

    /**
     * Gera a o conversa inicial que é o primeiro texto apresentado ao usuario.
     * @return {@link RespostaInvestBot}
     */
    public List<RespostaInvestBot> geraConversaInicial() {
        return Arrays.asList(
                new RespostaInvestBot("Oi, eu sou o InvestBot e posso te ajudar com valiosas informações sobre investimentos."),
                new RespostaInvestBot("Selecione uma das opcões do menu e deixe o trabalho duro comigo.", criaLayoutBotoesMenu())
        );
    }

    /**
     * Layout dos botões de iteração que serão apresentados ao usuario.
     *
     * @return retorna o layou no formato de {@link String[]}
     */
    private List<String[]> criaLayoutBotoesMenu() {
        return Arrays.asList(
                new String[]{"Taxa selic hoje", "Taxa selic acumulada nos últimos 30 dias"},
                new String[]{"Rendimento da poupanca nos últimos 30 dias", "Rendimento da poupança acumulado nos últimos 12 meses"}
        );
    }
}