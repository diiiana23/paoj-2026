package com.pao.laboratory09.exercise3;

import java.util.LinkedList;
import java.util.Queue;

public class CoadaTranzactii {
    private final Queue<String> q = new LinkedList<>();
    private final int CAPACITATE = 5;

    public synchronized void adauga(String t) throws InterruptedException {
        while (q.size() == CAPACITATE) {
            wait();
        }
        q.add(t);
        notifyAll();
    }
    public synchronized String extrage() throws InterruptedException {
        while (q.isEmpty()) {
            wait();
        }
        String t = q.poll();
        notifyAll();
        return t;
    }
}