package com.pao.laboratory09.exercise3;

public class ProcessorThread implements Runnable {
    public volatile boolean activ = true;
    private final CoadaTranzactii coada;

    public ProcessorThread(CoadaTranzactii coada) {
        this.coada = coada;
    }

    @Override
    public void run() {
        int id = 1;
        try {
            while (activ || true) {
                String t = coada.extrage();
                System.out.println("[Processor] Factura #" + id + " - " + t);
                id++;
                Thread.sleep(80);
                if (!activ && id>12)
                    break;
            }
        } catch (Exception e) {
            //thread stop
        }
    }
}