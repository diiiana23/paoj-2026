package com.pao.laboratory09.exercise3;

public class ATMThread extends Thread {
    private final int id;
    private final CoadaTranzactii coada;

    public ATMThread(int id, CoadaTranzactii coada) {
        this.id = id;
        this.coada = coada;
    }

    @Override
    public void run() {
        try {
            for(int i=1; i<=4; i++) {
                String t = "Tranzactie #" + i + " " + (100 * i) + " RON";
                if (i == 1)
                    System.out.println("[ATM-" + id + "] astept loc...");
                coada.adauga(t);
                System.out.println("[ATM-" + id + "] trimite: " + t);
                Thread.sleep(50);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}