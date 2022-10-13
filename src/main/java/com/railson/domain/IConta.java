package com.railson.domain;

import com.railson.exceptions.SaldoInsuficienteException;

public interface IConta {
    void sacar(double valor) throws SaldoInsuficienteException;

    void depositar(double valor);

    void transferir(double valor, IConta conta) throws SaldoInsuficienteException;

    void exibirExtrato();
}
