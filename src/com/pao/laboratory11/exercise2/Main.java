package com.pao.laboratory11.exercise2;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        try {
            run();
        } catch (IOException e) {}
    }
    private static void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(next(br));
        List<Tx> txs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] p = next(br).split("\\s+");

            txs.add(new Tx(
                    Integer.parseInt(p[0]),
                    Double.parseDouble(p[1]),
                    p[2],
                    p[3],
                    p[4],
                    p[5]
            ));
        }

        int q = Integer.parseInt(next(br));
        for (int i = 0; i < q; i++) {

            String[] p = next(br).split("\\s+");
            String op = p[0];

            switch (op)
            {
                case "REPORT_MONTH": {
                    String month = p[1];

                    double total = txs.stream()
                            .filter(t -> t.date.startsWith(month))
                            .mapToDouble(t -> t.amount)
                            .sum();

                    long count = txs.stream()
                            .filter(t -> t.date.startsWith(month))
                            .count();

                    System.out.printf(Locale.US,
                            "MONTH %s total=%.2f count=%d%n",
                            month, total, count);
                    break;
                }
                case "REPORT_ACCOUNT": {
                    String acc = p[1];

                    double total = txs.stream()
                            .filter(t -> t.account.equals(acc))
                            .mapToDouble(t -> t.amount)
                            .sum();

                    long count = txs.stream()
                            .filter(t -> t.account.equals(acc))
                            .count();

                    System.out.printf(Locale.US,
                            "ACCOUNT %s total=%.2f count=%d%n",
                            acc, total, count);
                    break;
                }
                case "TOP_CHANNELS": {
                    int k = Integer.parseInt(p[1]);

                    if (txs.isEmpty()) {
                        System.out.println("NONE");
                        break;
                    }

                    Map<String, Long> counts = txs.stream()
                            .collect(Collectors.groupingBy(
                                    t -> t.channel,
                                    Collectors.counting()
                            ));

                    counts.entrySet().stream()
                            .sorted(
                                    Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                                            .thenComparing(Map.Entry.comparingByKey())
                            )
                            .limit(k)
                            .forEach(e ->
                                    System.out.println(e.getKey() + " " + e.getValue())
                            );

                    break;
                }
                default:
            }
        }
    }

    private static String next(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.trim().isEmpty()) return line.trim();
        }
        return null;
    }

    private static class Tx {
        int id;
        double amount;
        String date;
        String country;
        String channel;
        String account;

        Tx(int id, double amount, String date, String country, String channel, String account) {
            this.id = id;
            this.amount = amount;
            this.date = date;
            this.country = country;
            this.channel = channel;
            this.account = account;
        }
    }
}