package br.com.fiap.mba.telegramBot.servicos;

import br.com.fiap.mba.telegramBot.domain.modelos.enums.Indexador;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HGFinanceService {

    private final String url;
    private final String token;
    private final OkHttpClient httpClient = new OkHttpClient();

    public HGFinanceService(String url, String token) {
        this.url = url;
        this.token = token;
    }

    public String ObterDadosDoIndexador(Indexador indexador){
        try {
            String requestUrl = this.url + indexador.getUrl() + (url.contains("?")?"&":"?") + "key="+ token;
            String retorno = sendGet(requestUrl);

            JsonElement jsonTree = JsonParser.parseString(retorno);

            System.out.println(jsonTree);
            /*Tratar retorno json*/
            return retorno;
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return "Não entendi...";
        }
    }

    private String sendGet(String url) throws Exception {

        Request request = new Request.Builder()
                .url(url).build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            String retorno = response.body().string();
            System.out.println(retorno);

            return retorno;
        }

    }
}
