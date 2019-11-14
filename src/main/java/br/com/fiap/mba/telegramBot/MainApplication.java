package br.com.fiap.mba.telegramBot;

import br.com.fiap.mba.bcbClient.BancoCentralBrasilClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);

            new BancoCentralBrasilClient().buscaHistoricoRendimentosPoupanca();
    }
}