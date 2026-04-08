package com.pao.laboratory07.exercise3;

import com.pao.laboratory07.exercise1.OrderState;

public abstract sealed class Comanda permits ComandaStandard, ComandaRedusa, ComandaGratuita {
    protected String nume;
    protected String client;
    protected double pret;
    protected OrderState stare = OrderState.PLACED;

    public Comanda(String nume, double pret, String client) {
        this.nume = nume;
        this.pret = pret;
        this.client = client;
    }

    public abstract double pretFinal();
    public abstract String descriere();

    public String getClient() { return client; }
}