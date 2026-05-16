package com.pao.laboratory11.exercise1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Predicate;

public class Main {
    private static final Set<String> HIGH_RISK_COUNTRIES =
            new HashSet<>(Arrays.asList("RU", "NG", "IR", "KP", "SY"));

    private static final Map<String, Integer> CHANNEL_SCORE = new HashMap<>();

    static {
        CHANNEL_SCORE.put("WEB", 15);
        CHANNEL_SCORE.put("APP", 10);
        CHANNEL_SCORE.put("CRYPTO", 30);
        CHANNEL_SCORE.put("POS", 5);
        CHANNEL_SCORE.put("ATM", 0);
    }

    private static final Predicate<Transaction> amountOverThreshold =
            t -> t.amount >= 1000;

    private static final Predicate<Transaction> countryInRisk =
            t -> HIGH_RISK_COUNTRIES.contains(t.country);

    private static final Predicate<Transaction> channelSuspicious =
            t -> t.channel.equals("WEB") ||
                    t.channel.equals("APP") ||
                    t.channel.equals("CRYPTO");

    private static final Predicate<Transaction> suspiciousRule =
            amountOverThreshold.or(countryInRisk).or(channelSuspicious);

    private static final Comparator<Transaction> BY_RISK_DESC_THEN_ID_ASC =
            Comparator.comparingInt(Main::riskScore)
                    .reversed()
                    .thenComparingInt(t -> t.id);

    public static void main(String[] args) {
        try {
            run();
        } catch (IOException e) {
            System.out.println("ERR IO");
        }
    }

    private static void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(readNonEmptyLine(br));

        Map<Integer, Transaction> byId = new HashMap<>();
        List<Transaction> all = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] tok = readNonEmptyLine(br).split("\\s+");
            Transaction tx = new Transaction(
                    Integer.parseInt(tok[0]),
                    Double.parseDouble(tok[1]),
                    tok[2],
                    tok[3],
                    tok[4]
            );
            byId.put(tx.id, tx);
            all.add(tx);
        }

        int q = Integer.parseInt(readNonEmptyLine(br));
        for (int i = 0; i < q; i++) {
            String[] cmd = readNonEmptyLine(br).split("\\s+");
            String op = cmd[0];
            switch (op) {
                case "CHECK": {
                    int id = Integer.parseInt(cmd[1]);
                    Transaction tx = byId.get(id);

                    if (tx == null) {
                        System.out.println("CHECK " + id + " => NOT_FOUND");
                    } else {
                        int score = riskScore(tx);
                        System.out.println("CHECK " + id + " => " + verdict(score) + " score=" + score);
                    }
                    break;
                }
                case "LIST_FLAGGED": {
                    List<Transaction> flagged = new ArrayList<>();

                    for (Transaction t : all) {
                        if (isFlagged(t)) {
                            flagged.add(t);
                        }
                    }

                    flagged.sort(BY_RISK_DESC_THEN_ID_ASC);

                    if (flagged.isEmpty()) {
                        System.out.println("NONE");
                    } else {
                        for (Transaction t : flagged) {
                            System.out.println(formatRiskLine(t));
                        }
                    }
                    break;
                }
                case "TOP_RISK": {
                    int k = Integer.parseInt(cmd[1]);

                    List<Transaction> ranked = new ArrayList<>(all);
                    ranked.sort(BY_RISK_DESC_THEN_ID_ASC);

                    int limit = Math.min(k, ranked.size());

                    for (int j = 0; j < limit; j++) {
                        System.out.println(formatRiskLine(ranked.get(j)));
                    }
                    break;
                }
                default:
                    System.out.println("ERR UNKNOWN_COMMAND");
            }
        }
    }

    private static int riskScore(Transaction tx) {
        int score = 0;

        if (tx.amount >= 5000) score += 70;
        else if (tx.amount >= 1000) score += 40;
        else if (tx.amount >= 500) score += 20;

        if (tx.amount <= 100) score += 5;
        if (HIGH_RISK_COUNTRIES.contains(tx.country)) score += 25;

        score += CHANNEL_SCORE.getOrDefault(tx.channel, 0);
        return score;
    }

    private static boolean isFlagged(Transaction tx) {
        return riskScore(tx) >= 60;
    }
    private static String verdict(int score) {
        return score >= 60 ? "FLAG" : "ALLOW";
    }
    private static String formatRiskLine(Transaction tx) {
        int score = riskScore(tx);
        return "[" + tx.id + "] " + verdict(score) + " score=" + score;
    }
    private static String readNonEmptyLine(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.trim().isEmpty()) return line.trim();
        }
        return null;
    }

    private static class Transaction {
        int id;
        double amount;
        String date;
        String country;
        String channel;

        Transaction(int id, double amount, String date, String country, String channel) {
            this.id = id;
            this.amount = amount;
            this.date = date;
            this.country = country;
            this.channel = channel;
        }
    }
}