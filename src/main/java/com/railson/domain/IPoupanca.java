package com.railson.domain;

import java.math.BigDecimal;

import com.railson.exceptions.SaldoInsuficienteException;

public interface IPoupanca {
    BigDecimal getSaldoPoupancaTotal();

    void calcularRendimento();

    void aplicarRendimento(double valor) throws SaldoInsuficienteException;

    void resgatarSaldoRendimento(double valor) throws SaldoInsuficienteException;
}
