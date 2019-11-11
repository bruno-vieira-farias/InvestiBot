package br.com.fiap.mba.telegramBot;

import br.com.fiap.mba.bcbClient.BancoCentralBasilClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
        try {
            new BancoCentralBasilClient().buscaHistoricoRendimentosPoupanca();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}