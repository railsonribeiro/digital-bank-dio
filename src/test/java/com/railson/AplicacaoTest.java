package com.railson;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.railson.domain.impl.ContaCorrente;
import com.railson.domain.impl.ContaPoupanca;
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
    public void realizarTransferenciaContaCorrenteTest() throws SaldoInsuficienteException {
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

    @Test
    public void realizarTransferenciaContaPoupancaTest() throws SaldoInsuficienteException {
        Aplicacao aplicacao = new Aplicacao();

        ContaPoupanca conta1 = new ContaPoupanca(null, null);
        ContaPoupanca conta2 = new ContaPoupanca(null, null);

        aplicacao.realizarDeposito(conta1, 100);

        aplicacao.realizarTransferencia(conta1, conta2, 60);

        BigDecimal expected1 = BigDecimal.valueOf(40);
        BigDecimal actual1 = conta1.getSaldoPoupanca();

        BigDecimal expected2 = BigDecimal.valueOf(60);
        BigDecimal actual2 = conta2.getSaldoPoupanca();

        assertTrue(actual1.compareTo(expected1) == 0);
        assertTrue(actual2.compareTo(expected2) == 0);

    }

    @Test
    public void realizarAplicacaoPoupancaTest() throws SaldoInsuficienteException {
        Aplicacao aplicacao = new Aplicacao();

        ContaPoupanca conta1 = new ContaPoupanca(null, null);
        
        aplicacao.realizarDeposito(conta1, 100);
        aplicacao.realizarAplicacaoPoupanca(conta1, 30);

        BigDecimal actual1 = conta1.getSaldoPoupanca();
        BigDecimal expected1 = BigDecimal.valueOf(70);

        BigDecimal actual2 = conta1.getSaldoAplicadoRendimento();
        BigDecimal expected2 = BigDecimal.valueOf(30);

        conta1.calcularRendimento();

        BigDecimal actual3 = conta1.getSaldoRendimento();
        BigDecimal expected3 = BigDecimal.ZERO;

        assertTrue(actual1.compareTo(expected1) == 0);
        assertTrue(actual2.compareTo(expected2) == 0);
        assertTrue(actual3.compareTo(expected3) == 0);

    }

    @Test
    public void realizarResgatePoupancaTest() throws SaldoInsuficienteException {
        Aplicacao aplicacao = new Aplicacao();

        ContaPoupanca conta1 = new ContaPoupanca(null, null);
        
        aplicacao.realizarDeposito(conta1, 150);
        aplicacao.realizarAplicacaoPoupanca(conta1, 30);

        BigDecimal actual1 = conta1.getSaldoPoupanca();
        BigDecimal expected1 = BigDecimal.valueOf(120);

        BigDecimal actual2 = conta1.getSaldoAplicadoRendimento();
        BigDecimal expected2 = BigDecimal.valueOf(30);

        aplicacao.realizarResgatePoupanca(conta1, 30);

        BigDecimal actual3 = conta1.getSaldoPoupanca();
        BigDecimal expected3 = BigDecimal.valueOf(150);
        
        assertTrue(actual1.compareTo(expected1) == 0);
        assertTrue(actual2.compareTo(expected2) == 0);
        assertTrue(actual3.compareTo(expected3) == 0);
        

    }


}
