package com.railson.domain.impl;

import java.math.BigDecimal;

import com.railson.domain.Conta;
import com.railson.domain.IConta;
import com.railson.exceptions.SaldoInsuficienteException;

public class ContaCorrente extends Conta {
    BigDecimal saldo;

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public ContaCorrente(String nomeTitular, String cpf) {
        super(nomeTitular, cpf);
        this.saldo = BigDecimal.ZERO;

    }

    @Override
    public void depositar(double valor) {
        this.setSaldo(this.getSaldo().add(BigDecimal.valueOf(valor)));
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException {
        int compararSaldos = BigDecimal.valueOf(valor).compareTo(this.getSaldo());
        if (compararSaldos != 1) {
            this.setSaldo(this.getSaldo().subtract(BigDecimal.valueOf(valor)));
            return;
        }
        throw new SaldoInsuficienteException(this.saldo, BigDecimal.valueOf(valor));

    }

    @Override
    public void transferir(double valor, IConta conta) throws SaldoInsuficienteException {

        this.sacar(valor);
        conta.depositar(valor);
    }

}
