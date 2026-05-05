package com.pao.laboratory09.exercise3;

public class Main {
    public static void main(String[] args) throws Exception {
        CoadaTranzactii coada = new CoadaTranzactii();

        ATMThread a1 = new ATMThread(1, coada);
        ATMThread a2 = new ATMThread(2, coada);
        ATMThread a3 = new ATMThread(3, coada);

        ProcessorThread processor = new ProcessorThread(coada);
        Thread pThread = new Thread(processor);

        a1.start();
        a2.start();
        a3.start();
        pThread.start();

        a1.join();
        a2.join();
        a3.join();

        processor.activ = false;
        synchronized (coada) {
            coada.notifyAll();
        }

        pThread.join();
        System.out.println("Toate tranzactiile procesate. Total: 12");
    }
}