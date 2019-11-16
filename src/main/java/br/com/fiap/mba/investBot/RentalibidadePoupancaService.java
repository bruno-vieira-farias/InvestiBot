package br.com.fiap.mba.investBot;

import br.com.fiap.mba.bcbClient.BancoCentralBrasilClient;
import br.com.fiap.mba.bcbClient.RendimentoPoupanca;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
        List<RendimentoPoupanca> RendimentoPoupancaNoPeriodo = bcbClient.buscaRendimentosPoupanca(LocalDate.now().minusMonths(12),LocalDate.now());
        return RendimentoPoupancaNoPeriodo
                .stream()
                .filter(rendimento -> rendimento.getData().getDayOfMonth() == LocalDate.now().getDayOfMonth() )
                .map(RendimentoPoupanca::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}