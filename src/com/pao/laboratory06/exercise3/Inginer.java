package com.pao.laboratory06.exercise3;

public class Inginer extends Angajat implements PlataOnline, Comparable<Inginer> {
    private double sold = 1000;

    public Inginer(String nume, String prenume, String telefon, double salariu) {
        super(nume, prenume, telefon, salariu);
    }

    @Override
    public void autentificare(String user, String parola) {
        if (user == null || parola == null || user.isEmpty() || parola.isEmpty()) {
            throw new IllegalArgumentException("Date invalide");
        }
        System.out.println("Autentificat: " + user);
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
    public int compareTo(Inginer o) {
        return this.nume.compareTo(o.nume);
    }
    @Override
    public String toString() {
        return nume + " " + prenume + " salariu=" + salariu;
    }
}