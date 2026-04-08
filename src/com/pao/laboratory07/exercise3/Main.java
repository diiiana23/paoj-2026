package com.pao.laboratory07.exercise3;

import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        List<Comanda> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] t = sc.nextLine().split(" ");
            switch (t[0]) {
                case "STANDARD" ->
                        list.add(new ComandaStandard(t[1], Double.parseDouble(t[2]), t[3]));
                case "DISCOUNTED" ->
                        list.add(new ComandaRedusa(t[1], Double.parseDouble(t[2]), Integer.parseInt(t[3]), t[4]));
                case "GIFT" ->
                        list.add(new ComandaGratuita(t[1], t[2]));
            }
        }

        list.forEach(c -> System.out.println(c.descriere()));

        while (true) {
            String cmd = sc.next();
            switch (cmd) {
                case "STATS" -> {
                    System.out.println("\n--- STATS ---");
                    Map<String, Double> medii =
                            list.stream().collect(Collectors.groupingBy(
                                    c -> {
                                        if (c instanceof ComandaStandard) return "STANDARD";
                                        if (c instanceof ComandaRedusa) return "DISCOUNTED";
                                        return "GIFT";
                                    },
                                    Collectors.averagingDouble(Comanda::pretFinal)
                            ));
                    medii.forEach((k, v) ->
                            System.out.printf("%s: medie = %.2f lei\n", k, v));
                }
                case "FILTER" -> {
                    double th = sc.nextDouble();
                    System.out.printf("\n--- FILTER (>= %.2f) ---\n", th);
                    list.stream()
                            .filter(c -> c.pretFinal() >= th)
                            .forEach(c -> System.out.println(
                                    c.descriere().replace("[" + c.stare + "] ", "")
                            ));
                }
                case "SORT" -> {
                    System.out.println("\n--- SORT ---");
                    list.stream()
                            .sorted(Comparator.comparing(Comanda::getClient)
                                    .thenComparing(Comanda::pretFinal))
                            .forEach(c -> System.out.println(
                                    c.descriere().replace("[" + c.stare + "] ", "")
                            ));
                }
                case "SPECIAL" -> {
                    System.out.println("\n--- SPECIAL ---");
                    list.stream()
                            .filter(c -> c instanceof ComandaRedusa cr && cr.getDiscount() > 15)
                            .forEach(c -> System.out.println(
                                    c.descriere().replace("[" + c.stare + "] ", "")
                            ));
                }
                case "QUIT" -> {
                    return;
                }
            }
        }
    }
}