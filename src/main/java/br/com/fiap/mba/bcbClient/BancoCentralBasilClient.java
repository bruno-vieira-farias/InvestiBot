package br.com.fiap.mba.bcbClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

public class BancoCentralBasilClient {
    private OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public List<RendimentoPoupanca> buscaHistoricoRendimentosPoupanca() throws IOException {
        HttpUrl url2 = new HttpUrl.Builder()
                .scheme("https")
                .host("api.bcb.gov.br")
                .addPathSegment("dados")
                .addPathSegment("serie")
                .addPathSegment("bcdata.sgs.195")
                .addPathSegment("dados")
                .addQueryParameter("dataInicial", "01/01/2019")
                .addQueryParameter("dataFinal", "01/10/2019")
                .build();

        Request request = new Request.Builder()
                .url(url2)
                .build();

        ResponseBody response = client.newCall(request).execute().body();
        return objectMapper.readValue(response.string(), objectMapper.getTypeFactory().constructCollectionType(List.class, RendimentoPoupanca.class));
    }
}