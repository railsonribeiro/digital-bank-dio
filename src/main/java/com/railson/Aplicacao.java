package com.railson;

import java.math.BigDecimal;

import com.railson.domain.IAplicacao;
import com.railson.domain.IConta;
import com.railson.domain.IPoupanca;
import com.railson.domain.impl.ContaPoupanca;
import com.railson.exceptions.SaldoInsuficienteException;

public class Aplicacao implements IAplicacao {
    public static void main(String[] args) throws SaldoInsuficienteException {
        // REALIZAR AS OPERAÇÕES VIA INSTANCIA DA CLASSE APLICAÇÃO

        // Aplicacao aplicacao = new Aplicacao();

        // ContaPoupanca conta1 = new ContaPoupanca(null, null);
        // ContaPoupanca conta2 = new ContaPoupanca(null, null);

        // aplicacao.realizarDeposito(conta1, 100);

        // aplicacao.realizarTransferencia(conta1, conta2, 60);

        // BigDecimal expected1 = BigDecimal.valueOf(40);
        // BigDecimal actual1 = conta1.getSaldoPoupanca();

        // BigDecimal expected2 = BigDecimal.valueOf(60);
        // BigDecimal actual2 = conta2.getSaldoPoupanca();

        // System.out.println(expected1);
        // System.out.println(actual1);

    }

    @Override
    public void realizarSaque(IConta conta, double valor) throws SaldoInsuficienteException {
        conta.sacar(valor);

    }

    @Override
    public void realizarDeposito(IConta conta, double valor) {
        conta.depositar(valor);

    }

    @Override
    public void realizarTransferencia(IConta contaOrigem, IConta contaDeposito, double valor)
            throws SaldoInsuficienteException {
        contaOrigem.transferir(valor, contaDeposito);

    }

    @Override
    public void realizarAplicacaoPoupanca(IPoupanca conta, double valor) throws SaldoInsuficienteException {
        conta.aplicarRendimento(valor);

    }

    @Override
    public void realizarResgatePoupanca(IPoupanca conta, double valor) throws SaldoInsuficienteException {
        conta.resgatarSaldoRendimento(valor);

    }

}
