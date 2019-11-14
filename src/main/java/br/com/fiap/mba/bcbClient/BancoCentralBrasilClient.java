package br.com.fiap.mba.bcbClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kong.unirest.GenericType;
import kong.unirest.JacksonObjectMapper;
import kong.unirest.Unirest;

import java.util.List;

public class BancoCentralBrasilClient {

    public BancoCentralBrasilClient() {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());

        Unirest.config().setObjectMapper( new JacksonObjectMapper(mapper));
    }

    public List<RendimentoPoupanca> buscaHistoricoRendimentosPoupanca(){
        return  Unirest.get("https://api.bcb.gov.br/dados/serie/bcdata.sgs.195/dados")
                .header("accept", "application/json")
                .queryString("dataInicial", "01/01/2019")
                .queryString("dataFinal", "01/10/2019")
                .asObject(new GenericType<List<RendimentoPoupanca>>(){})
                .getBody();
    }
}