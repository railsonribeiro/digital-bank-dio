package com.railson;

import com.railson.domain.IAplicacao;
import com.railson.domain.IConta;
import com.railson.domain.IPoupanca;
import com.railson.exceptions.SaldoInsuficienteException;

public class Aplicacao implements IAplicacao {
    public static void main(String[] args) throws SaldoInsuficienteException {
        // REALIZAR AS OPERAÇÕES VIA INSTANCIA DA CLASSE APLICAÇÃO

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
