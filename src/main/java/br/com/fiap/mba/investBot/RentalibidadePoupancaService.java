package br.com.fiap.mba.investBot;

import br.com.fiap.mba.bcbClient.BancoCentralBrasilClient;
import br.com.fiap.mba.bcbClient.RendimentoMensalPoupanca;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
/**
 * Agrupamento de serviços aplicados sobre a rentabilidade da poupança.
 */
@Service
public class RentalibidadePoupancaService {
    private BancoCentralBrasilClient bcbClient;

    public RentalibidadePoupancaService(BancoCentralBrasilClient bcbClient) {
        this.bcbClient = bcbClient;
    }

    public BigDecimal obtemRendimentoPoupancaUltimosTrintaDias() {
        return bcbClient.buscaRendimentoPoupanca(LocalDate.now()).getValor();
    }

    public BigDecimal obtemRendimentoPoupancaUltimosDozeMeses(){
        List<RendimentoMensalPoupanca> rendimentoMensalPoupancaNoPeriodo = bcbClient.buscaRendimentosPoupanca(LocalDate.now().minusMonths(12),LocalDate.now());
        return rendimentoMensalPoupancaNoPeriodo
                .stream()
                .filter(rendimento -> rendimento.getData().getDayOfMonth() == LocalDate.now().getDayOfMonth() )
                .map(RendimentoMensalPoupanca::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}