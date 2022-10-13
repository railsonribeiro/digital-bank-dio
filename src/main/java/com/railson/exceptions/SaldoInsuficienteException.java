package com.railson.exceptions;

import java.math.BigDecimal;

public class SaldoInsuficienteException extends Exception {

    public SaldoInsuficienteException(BigDecimal saldoAtual, BigDecimal valorTransacao) {
        super(String.format("Não foi possível realizar a transação. Saldo atual: %.2f Valor da transação: %.2f",
                saldoAtual, valorTransacao));
    }

}
