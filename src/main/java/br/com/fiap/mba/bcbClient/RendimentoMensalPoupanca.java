package br.com.fiap.mba.bcbClient;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Representa um rendimento mensal da poupança.
 */
public class RendimentoMensalPoupanca {
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate datafim;
    private BigDecimal valor;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalDate getDatafim() {
        return datafim;
    }

    public void setDatafim(LocalDate datafim) {
        this.datafim = datafim;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}