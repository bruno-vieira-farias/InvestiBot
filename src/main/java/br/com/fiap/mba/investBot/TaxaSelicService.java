package br.com.fiap.mba.investBot;

import br.com.fiap.mba.bcbClient.BancoCentralBrasilClient;
import br.com.fiap.mba.bcbClient.TaxaSelic;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Agrupamento de serviços sobre a taxa Selic.
 */
@Service
public class TaxaSelicService {
    private BancoCentralBrasilClient bcbClient;

    public TaxaSelicService(BancoCentralBrasilClient bcbClient) {
        this.bcbClient = bcbClient;
    }

    public BigDecimal obtemTaxaSelicDia(){
        return bcbClient.buscaTaxaSelic(LocalDate.now()).getValor();
    }

    public BigDecimal obtemTaxaSelicUltimosTrintaDias(){
        List<TaxaSelic> taxaSelicNoPeriodo = bcbClient.buscaTaxaSelic(LocalDate.now().minusDays(30),LocalDate.now());
        return taxaSelicNoPeriodo.stream().map(it -> it.getValor()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
