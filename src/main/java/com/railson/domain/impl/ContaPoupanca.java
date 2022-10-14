package com.railson.domain.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import com.railson.domain.Conta;
import com.railson.domain.IConta;
import com.railson.domain.IPoupanca;
import com.railson.exceptions.SaldoInsuficienteException;

public class ContaPoupanca extends Conta implements IPoupanca {
    private final BigDecimal TAXA_RENDIMENTO = BigDecimal.valueOf(0.005);
    private BigDecimal saldoPoupanca;
    private BigDecimal saldoRendimento;
    private BigDecimal saldoAplicadoRendimento;

    public ContaPoupanca(String nomeTitular, String cpf) {
        super(nomeTitular, cpf);
        saldoPoupanca = BigDecimal.ZERO;
        saldoRendimento = BigDecimal.ZERO;
        saldoAplicadoRendimento = BigDecimal.ZERO;
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException {

        int verificarSaldoSuficiente = BigDecimal.valueOf(valor).compareTo(getSaldoPoupanca());
        if (verificarSaldoSuficiente != 1) {
            setSaldoPoupanca(getSaldoPoupanca().subtract(BigDecimal.valueOf(valor)));
            return;
        }
        throw new SaldoInsuficienteException(this.getSaldoPoupancaTotal(), BigDecimal.valueOf(valor));

    }

    @Override
    public void aplicarRendimento(double valor) throws SaldoInsuficienteException {
        int verificarSaldoSuficiente = BigDecimal.valueOf(valor).compareTo(getSaldoPoupanca());
        if (verificarSaldoSuficiente != 1) {
            this.setSaldoPoupanca(getSaldoPoupanca().subtract(BigDecimal.valueOf(valor)));
            saldoAplicadoRendimento = this.saldoAplicadoRendimento.add(BigDecimal.valueOf(valor));
            return;
        }
        throw new SaldoInsuficienteException(getSaldoPoupanca(), BigDecimal.valueOf(valor));
    }

    @Override
    public void depositar(double valor) {
        setSaldoPoupanca(getSaldoPoupanca().add(BigDecimal.valueOf(valor)));
    }

    @Override
    public void transferir(double valor, IConta conta) throws SaldoInsuficienteException {
        this.sacar(valor);
        conta.depositar(valor);
    }

    @Override
    public void calcularRendimento() {

        LocalDate dataAtual = LocalDate.now(); // TODO: RETIRAR
        Period periodo = Period.between(super.getDataAbertura(), dataAtual);

        int totalDias = periodo.getYears() * 365 + periodo.getMonths() * 30 + periodo.getDays();
        BigDecimal rendimento = BigDecimal.valueOf(totalDias / 30.0).multiply(TAXA_RENDIMENTO)
                .multiply(this.getSaldoAplicadoRendimento());

        this.saldoRendimento = rendimento;
    }

    public BigDecimal getSaldoRendimento() {
        return saldoRendimento;
    }

    public BigDecimal getSaldoPoupanca() {
        return saldoPoupanca;
    }

    @Override
    public BigDecimal getSaldoPoupancaTotal() {
        return this.getSaldoPoupanca().add(getSaldoRendimento()).add(getSaldoAplicadoRendimento());
    }

    public BigDecimal getSaldoAplicadoRendimento() {
        return saldoAplicadoRendimento;
    }

    public void setSaldoAplicadoRendimento(BigDecimal saldoAplicadoRendimento) {
        this.saldoAplicadoRendimento = saldoAplicadoRendimento;
    }

    public void setSaldoPoupanca(BigDecimal saldoPoupanca) {
        this.saldoPoupanca = saldoPoupanca;
    }

    public void setSaldoRendimento(BigDecimal saldoRendimento) {
        this.saldoRendimento = saldoRendimento;
    }

    @Override
    public void resgatarSaldoRendimento(double valor) throws SaldoInsuficienteException {
        calcularRendimento();
        if (BigDecimal.valueOf(valor).compareTo(getSaldoAplicadoRendimento().add(getSaldoRendimento())) != 1) {

            if (BigDecimal.valueOf(valor).compareTo(getSaldoAplicadoRendimento()) == 1) {
                BigDecimal diferenca = BigDecimal.valueOf(valor).subtract(getSaldoAplicadoRendimento());
                setSaldoPoupanca(getSaldoPoupanca().add(getSaldoAplicadoRendimento()));
                setSaldoAplicadoRendimento(BigDecimal.ZERO);
                setSaldoPoupanca(getSaldoPoupanca().add(diferenca));
                return;
            }

            setSaldoPoupanca(getSaldoPoupanca().add((BigDecimal.valueOf(valor))));
            setSaldoAplicadoRendimento(getSaldoAplicadoRendimento().subtract(TAXA_RENDIMENTO));

            return;
        }
        throw new SaldoInsuficienteException(getSaldoAplicadoRendimento().add(getSaldoRendimento()),
                BigDecimal.valueOf(valor));
    }

}
