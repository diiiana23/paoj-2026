package com.pao.laboratory10.exercise3;

import java.util.*;
import java.util.stream.*;
import java.util.Locale;

public class Main {
    static class Tranzactie {
        int id;
        double suma;
        String data;
        String tip;
        String contSursa;

        public Tranzactie(int id, double suma, String data, String tip, String contSursa) {
            this.id = id;
            this.suma = suma;
            this.data = data;
            this.tip = tip;
            this.contSursa = contSursa;
        }

        public double getSuma() { return suma; }
        public String getData() { return data; }
        public String getTip() { return tip; }
        public String getContSursa() { return contSursa; }

        @Override
        public String toString() {
            return String.format(Locale.US, "[%d] %s %s: %.2f RON (%s)", id, data, tip, suma, contSursa);
        }
    }

    public static void main(String[] args) {
        List<Tranzactie> lista = List.of(
                new Tranzactie(1, 500.0, "2024-01-10", "CREDIT", "CONT_A"),
                new Tranzactie(2, 300.0, "2024-01-15", "DEBIT", "CONT_B"),
                new Tranzactie(3, 150.0, "2024-02-01", "CREDIT", "CONT_A"),
                new Tranzactie(4, 700.0, "2024-02-10", "DEBIT", "CONT_C"),
                new Tranzactie(5, 1200.0, "2024-03-05", "CREDIT", "CONT_B"),
                new Tranzactie(6, 50.0, "2024-03-07", "DEBIT", "CONT_A"),
                new Tranzactie(7, 900.0, "2024-01-20", "CREDIT", "CONT_D"),
                new Tranzactie(8, 400.0, "2024-02-15", "DEBIT", "CONT_B"),
                new Tranzactie(9, 250.0, "2024-03-10", "CREDIT", "CONT_C"),
                new Tranzactie(10, 100.0, "2024-02-20", "DEBIT", "CONT_D")
        );

        System.out.println("=== Tranzactii CREDIT ===");
        lista.stream()
                .filter(t -> t.getTip().equals("CREDIT"))
                .forEach(System.out::println);

        System.out.println("\n=== Total procesat ===");
        double total = lista.stream()
                .mapToDouble(Tranzactie::getSuma)
                .sum();
        System.out.printf(Locale.US, "Total procesat: %.2f RON%n", total);

        System.out.println("\n=== Suma pe luni ===");
        Map<String, Double> sumaPeLuna = lista.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getData().substring(0, 7),
                        TreeMap::new,
                        Collectors.summingDouble(Tranzactie::getSuma)
                ));

        sumaPeLuna.forEach((luna, suma) ->
                System.out.printf(Locale.US, "%s: %.2f RON%n", luna, suma)
        );

        System.out.println("\n=== Top 3 tranzactii ===");
        lista.stream()
                .sorted((a, b) -> Double.compare(b.getSuma(), a.getSuma()))
                .limit(3)
                .forEach(System.out::println);

        System.out.println("\n=== Conturi sursa unice ===");
        List<String> conturi = lista.stream()
                .map(Tranzactie::getContSursa)
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Conturi sursa unice: " + conturi);

        System.out.println("\n=== Suma medie ===");
        double medie = lista.stream()
                .mapToDouble(Tranzactie::getSuma)
                .average()
                .orElse(0.0);

        System.out.printf(Locale.US, "Suma medie: %.2f RON%n", medie);

        System.out.println("\n=== Extras de cont pe luni ===");

        Map<String, List<Tranzactie>> grupat = lista.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getData().substring(0, 7),
                        TreeMap::new,
                        Collectors.toList()
                ));

        grupat.forEach((luna, tranzactii) -> {
            double suma = tranzactii.stream()
                    .mapToDouble(Tranzactie::getSuma)
                    .sum();

            System.out.printf(Locale.US,
                    "EXTRAS DE CONT - %s: %d tranzactii, total: %.2f RON%n",
                    luna, tranzactii.size(), suma);
        });
    }
}