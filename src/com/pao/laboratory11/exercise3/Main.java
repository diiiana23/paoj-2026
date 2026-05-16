package com.pao.laboratory11.exercise3;

import java.util.*;
import java.util.stream.*;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        List<Transaction> data = List.of(
                new Transaction(1, 500.0, "RO", "WEB"),
                new Transaction(2, 1200.0, "RO", "APP"),
                new Transaction(3, 300.0, "NL", "WEB"),
                new Transaction(4, 700.0, "RO", "CRYPTO"),
                new Transaction(5, 2000.0, "US", "WEB"),
                new Transaction(6, 150.0, "NL", "APP")
        );
        Snapshot snap = data.stream().collect(CustomCollectors.toSnapshot(3));

        System.out.println("=== Top tranzactii ===");
        snap.getTop().forEach(System.out::println);

        System.out.println("\n=== Count by country ===");
        snap.getByCountry().entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));

        System.out.println("\n=== Count by channel ===");
        snap.getByChannel().entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));

        System.out.printf(Locale.US, "\nTotal: %.2f%n", snap.getTotal());
    }

    static class Transaction {
        int id;
        double amount;
        String country;
        String channel;

        Transaction(int id, double amount, String country, String channel) {
            this.id = id;
            this.amount = amount;
            this.country = country;
            this.channel = channel;
        }

        public double getAmount() { return amount; }
        public String getCountry() { return country; }
        public String getChannel() { return channel; }

        @Override
        public String toString() {
            return "[" + id + "] " + amount + " " + country + " " + channel;
        }
    }

    static class Snapshot {
        private final Map<String, Long> byCountry;
        private final Map<String, Long> byChannel;
        private final double total;
        private final List<Transaction> top;

        Snapshot(Map<String, Long> c, Map<String, Long> ch, double total, List<Transaction> top) {
            this.byCountry = Collections.unmodifiableMap(c);
            this.byChannel = Collections.unmodifiableMap(ch);
            this.total = total;
            this.top = List.copyOf(top);
        }

        public Map<String, Long> getByCountry() { return byCountry; }
        public Map<String, Long> getByChannel() { return byChannel; }
        public double getTotal() { return total; }
        public List<Transaction> getTop() { return top; }
    }

    static class CustomCollectors {
        public static Collector<Transaction, ?, Snapshot> toSnapshot(int topN) {

            class Agg {
                Map<String, Long> byCountry = new HashMap<>();
                Map<String, Long> byChannel = new HashMap<>();
                double total = 0;
                List<Transaction> list = new ArrayList<>();
            }

            return Collector.of(
                    Agg::new,
                    (agg, tx) -> {
                        agg.total += tx.amount;

                        agg.byCountry.put(tx.country,
                                agg.byCountry.getOrDefault(tx.country, 0L) + 1);

                        agg.byChannel.put(tx.channel,
                                agg.byChannel.getOrDefault(tx.channel, 0L) + 1);

                        agg.list.add(tx);
                    },

                    (a, b) -> {
                        b.byCountry.forEach((k, v) ->
                                a.byCountry.put(k, a.byCountry.getOrDefault(k, 0L) + v));

                        b.byChannel.forEach((k, v) ->
                                a.byChannel.put(k, a.byChannel.getOrDefault(k, 0L) + v));

                        a.total += b.total;
                        a.list.addAll(b.list);

                        return a;
                    },

                    agg -> {
                        List<Transaction> top = agg.list.stream()
                                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                                .limit(topN)
                                .toList();

                        return new Snapshot(
                                agg.byCountry,
                                agg.byChannel,
                                agg.total,
                                top
                        );
                    }
            );
        }
    }
}