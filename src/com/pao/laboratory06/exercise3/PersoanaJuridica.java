package com.pao.laboratory06.exercise3;

import java.util.ArrayList;
import java.util.List;

public class PersoanaJuridica extends Persoana implements PlataOnlineSMS {
    private double sold = 5000;
    private List<String> smsTrimise = new ArrayList<>();

    public PersoanaJuridica(String nume, String prenume, String telefon) {
        super(nume, prenume, telefon);
    }

    @Override
    public void autentificare(String user, String parola) {
        if (user == null || parola == null || user.isEmpty() || parola.isEmpty()) {
            throw new IllegalArgumentException("Date invalide");
        }
        System.out.println("Autentificat PJ: " + user);
    }
    @Override
    public double consultareSold() {
        return sold;
    }
    @Override
    public boolean efectuarePlata(double suma) {
        if (suma <= 0) return false;
        if (sold >= suma) {
            sold -= suma;
            return true;
        }
        return false;
    }
    @Override
    public boolean trimiteSMS(String mesaj) {
        if (telefon == null || telefon.isEmpty() || mesaj == null || mesaj.isEmpty()) {
            return false;
        }
        smsTrimise.add(mesaj);
        return true;
    }

    public List<String> getSmsTrimise() {
        return smsTrimise;
    }
}