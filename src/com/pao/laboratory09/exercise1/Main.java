package com.pao.laboratory09.exercise1;

import java.io.*;
import java.util.*;

public class Main {
    private static final String OUTPUT_FILE = "output/lab09_ex1.ser";

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Tranzactie> list = new ArrayList<>();

        for (int i=0; i<n; i++) {
            String[] p = sc.nextLine().split(" ");

            Tranzactie t = new Tranzactie();
            t.id = Integer.parseInt(p[0]);
            t.suma = Double.parseDouble(p[1]);
            t.data = p[2];
            t.contSursa = p[3];
            t.contDestinatie = p[4];
            t.tip = TipTranzactie.valueOf(p[5]);
            t.note = "procesat";
            list.add(t);
        }
        new File("output").mkdirs();

        //SERIALIZARE
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(OUTPUT_FILE))) {
            out.writeObject(list);
        }
        //DESERIALIZARE
        List<Tranzactie> data;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(OUTPUT_FILE))) {
            data = (List<Tranzactie>) in.readObject();
        }
        //COMENZI
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty())
                continue;

            String[] cmd = line.split(" ");
            switch (cmd[0]) {
                case "LIST":
                    for (Tranzactie t : data) {
                        print(t);
                    }
                    break;
                case "FILTER":
                    String prefix = cmd[1];
                    boolean ok = false;
                    for (Tranzactie t : data) {
                        if (t.data.startsWith(prefix)) {
                            print(t);
                            ok = true;
                        }
                    }
                    if (!ok)
                        System.out.println("Niciun rezultat.");
                    break;
                case "NOTE":
                    int id = Integer.parseInt(cmd[1]);
                    boolean found = false;
                    for (Tranzactie t : data) {
                        if (t.id == id) {
                            System.out.println("NOTE[" + id + "]: " + t.note);
                            found = true;
                            break;
                        }
                    }
                    if (!found)
                        System.out.println("NOTE[" + id + "]: not found");
                    break;
            }
        }
    }

    private static void print(Tranzactie t) {
        System.out.println("[" + t.id + "] " + t.data + " " + t.tip + ": " + String.format(Locale.US, "%.2f", t.suma) + " RON | " + t.contSursa + " -> " + t.contDestinatie
        );
    }
}