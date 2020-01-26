package br.com.fiap.mba.hgfinanceClient;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class HgFinanceApiClient {

    public static String ObterDadosTicker(String ticker){
        HttpResponse<JsonNode> response = Unirest.get("https://api.hgbrasil.com/finance/stock_price?key=d34d7fc3&symbol=")
                .header("accept", "application/json")
                .queryString("key", "d34d7fc3")
                .queryString("symbol", ticker)
                //.field("parameter", "value")
                //.field("foo", "bar")
                .asJson();

        System.out.println(response.toString());

        return response.toString();
    }
}
