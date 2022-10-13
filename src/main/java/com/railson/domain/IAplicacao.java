package com.railson.domain;

import com.railson.exceptions.SaldoInsuficienteException;

public interface IAplicacao {
    void realizarSaque(IConta conta, double valor) throws SaldoInsuficienteException;

    void realizarDeposito(IConta conta, double valor);

    void realizarTransferencia(IConta contaOrigem, IConta contaDeposito, double valor)
            throws SaldoInsuficienteException;

    void realizarAplicacaoPoupanca(IPoupanca conta, double valor) throws SaldoInsuficienteException;

    void realizarResgatePoupanca(IPoupanca conta, double valor) throws SaldoInsuficienteException;
}
