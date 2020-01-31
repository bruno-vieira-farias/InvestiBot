package br.com.fiap.mba.bcbClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kong.unirest.GenericType;
import kong.unirest.JacksonObjectMapper;
import kong.unirest.Unirest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Consulta as taxas de juros de rendimentos da poupança no Banco Central do Brasil.
 */
public class BancoCentralBrasilClient {
    private final String URL_POUPANCA = "https://api.bcb.gov.br/dados/serie/bcdata.sgs.195/dados";
    private final String URL_SELIC = "https://api.bcb.gov.br/dados/serie/bcdata.sgs.11/dados?formato=json";
    private DateTimeFormatter formatador;

    public BancoCentralBrasilClient() {
        setup();
    }

    /**
     * Busca todos os {@link RendimentoMensalPoupanca} em um determinado período.
     *
     * @param dataInicial do periodo de busca
     * @param dataFinal   do periodo de busca
     * @return lista de {@link RendimentoMensalPoupanca}
     */
    public List<RendimentoMensalPoupanca> buscaRendimentosPoupanca(LocalDate dataInicial, LocalDate dataFinal) {
        return Unirest.get(URL_POUPANCA)
                .header("accept", "application/json")
                .queryString("dataInicial", formatador.format(dataInicial))
                .queryString("dataFinal", formatador.format(dataFinal))
                .asObject(new GenericType<List<RendimentoMensalPoupanca>>() {
                })
                .getBody();
    }

    /**
     * Busca um {@link RendimentoMensalPoupanca} de uma data específica.
     *
     * @param data da busca
     * @return retorna um {@link RendimentoMensalPoupanca}
     */
    public RendimentoMensalPoupanca buscaRendimentoPoupanca(LocalDate data) {
        return Unirest.get(URL_POUPANCA)
                .header("accept", "application/json")
                .queryString("dataInicial", formatador.format(data))
                .queryString("dataFinal", formatador.format(data))
                .asObject(new GenericType<List<RendimentoMensalPoupanca>>() {
                })
                .getBody()
                .get(0);
    }

    /**
     * Busca todas as {@link TaxaSelic} de um determinado período.
     *
     * @param dataInicial do periodo de busca
     * @param dataFinal   do periodo de busca
     * @return lista de {@link TaxaSelic}
     */
    public List<TaxaSelic> buscaTaxaSelic(LocalDate dataInicial, LocalDate dataFinal) {
        return Unirest.get(URL_SELIC)
                .header("accept", "application/json")
                .queryString("dataInicial", formatador.format(dataInicial))
                .queryString("dataFinal", formatador.format(dataFinal))
                .asObject(new GenericType<List<TaxaSelic>>() {
                })
                .getBody();
    }

    /**
     * Busca uma {@link TaxaSelic} de uma data específica.
     *
     * @param data da busca
     * @return retorna um {@link TaxaSelic}
     */
    public TaxaSelic buscaTaxaSelic(LocalDate data) {
        return Unirest.get(URL_SELIC)
                .header("accept", "application/json")
                .queryString("dataInicial", formatador.format(data))
                .queryString("dataFinal", formatador.format(data))
                .asObject(new GenericType<List<TaxaSelic>>() {
                })
                .getBody()
                .get(0);
    }


    private void setup() {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());

        Unirest.config().setObjectMapper(new JacksonObjectMapper(mapper));
        this.formatador = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    }
}