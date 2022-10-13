package com.railson.domain;

import java.time.LocalDate;

public abstract class Conta implements IConta {

    private String nomeTitular;
    private String cpf;
    private final LocalDate DATA_ABERTURA = LocalDate.now();
    private boolean ativa = false;

    public Conta(String nomeTitular, String cpf) {
        this.nomeTitular = nomeTitular;
        this.cpf = cpf;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataAbertura() {
        return DATA_ABERTURA;
    }

    @Override
    public void exibirExtrato() {
        System.out.println(this);
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

}
