package com.railson;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.railson.domain.impl.ContaCorrente;
import com.railson.exceptions.SaldoInsuficienteException;

public class AplicacaoTest {

    @Test
    public void saldoInsuficienteExceptionSaqueTest() {
        Aplicacao aplicacao = new Aplicacao();
        ContaCorrente conta = new ContaCorrente(null, null);

        SaldoInsuficienteException exception = assertThrows(SaldoInsuficienteException.class,
                () -> aplicacao.realizarSaque(conta, 100));

        assertTrue(exception.getMessage()
                .equals("Não foi possível realizar a transação. Saldo atual: 0,00 Valor da transação: 100,00"));
    }

    @Test
    public void realizarDepositoTest() {
        Aplicacao aplicacao = new Aplicacao();
        ContaCorrente conta = new ContaCorrente(null, null);
        aplicacao.realizarDeposito(conta, 100);

        BigDecimal expected = BigDecimal.valueOf(100);
        BigDecimal actual = conta.getSaldo();

        assertTrue(actual.compareTo(expected) == 0);
    }

    @Test
    public void realizarSaqueTest() throws SaldoInsuficienteException {
        Aplicacao aplicacao = new Aplicacao();
        ContaCorrente conta = new ContaCorrente(null, null);
        aplicacao.realizarDeposito(conta, 100);
        aplicacao.realizarSaque(conta, 75);

        BigDecimal expected = BigDecimal.valueOf(25);
        BigDecimal actual = conta.getSaldo();

        assertTrue(actual.compareTo(expected) == 0);

    }

    @Test

    public void realizarTransferenciaTest() throws SaldoInsuficienteException {
        Aplicacao aplicacao = new Aplicacao();

        ContaCorrente conta1 = new ContaCorrente(null, null);
        ContaCorrente conta2 = new ContaCorrente(null, null);

        aplicacao.realizarDeposito(conta1, 100);

        aplicacao.realizarTransferencia(conta1, conta2, 60);

        BigDecimal expected1 = BigDecimal.valueOf(40);
        BigDecimal actual1 = conta1.getSaldo();

        BigDecimal expected2 = BigDecimal.valueOf(60);
        BigDecimal actual2 = conta2.getSaldo();

        assertTrue(actual1.compareTo(expected1) == 0);
        assertTrue(actual2.compareTo(expected2) == 0);

    }
}
