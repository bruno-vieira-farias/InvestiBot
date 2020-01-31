package br.com.fiap.mba.bcbClient;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Representa a taxa selic diaria de um dia específico.
 */
public class TaxaSelic {
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    private BigDecimal valor;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
