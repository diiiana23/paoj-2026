package com.pao.laboratory10.exercise2;

import com.pao.laboratory10.exercise1.Tranzactie;
import com.pao.laboratory10.exercise1.TipTranzactie;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Tranzactie> lista = new ArrayList<>();
        int N = Integer.parseInt(sc.nextLine().trim());
        for (int i = 0; i < N; i++)
        {
            String line = sc.nextLine().trim();
            String[] parts = line.split(" ");

            int id = Integer.parseInt(parts[0]);
            double suma = Double.parseDouble(parts[1]);
            String data = parts[2];
            TipTranzactie tip = TipTranzactie.valueOf(parts[3]);

            lista.add(new Tranzactie(id, suma, data, tip));
        }

        while (sc.hasNextLine())
        {
            String line = sc.nextLine().trim();
            if (line.isEmpty())
                continue;
            String[] parts = line.split(" ");
            String cmd = parts[0];
            switch (cmd)
            {
                case "UNIQUE_IDS": {
                    LinkedHashSet<Integer> set = new LinkedHashSet<>();
                    for (Tranzactie t : lista) {
                        set.add(t.getId());
                    }
                    System.out.println("IDs unice (" + set.size() + "): " + set);
                    break;
                }
                case "MONTHLY_REPORT": {
                    TreeMap<String, double[]> map = new TreeMap<>();

                    for (Tranzactie t : lista) {
                        String luna = t.getData().substring(0, 7);

                        map.putIfAbsent(luna, new double[]{0.0, 0.0});

                        if (t.getTip() == TipTranzactie.CREDIT) {
                            map.get(luna)[0] += t.getSuma();
                        } else {
                            map.get(luna)[1] += t.getSuma();
                        }
                    }
                    for (String luna : map.keySet()) {
                        double credit = map.get(luna)[0];
                        double debit = map.get(luna)[1];

                        System.out.printf(Locale.US,
                                "%s: CREDIT %.2f RON, DEBIT %.2f RON%n",
                                luna, credit, debit);
                    }
                    break;
                }
                case "TOP": {
                    int n = Integer.parseInt(parts[1]);

                    List<Tranzactie> copie = new ArrayList<>(lista);
                    copie.sort((a, b) -> Double.compare(b.getSuma(), a.getSuma()));

                    System.out.println("Top " + n + ":");
                    for (int i = 0; i < Math.min(n, copie.size()); i++) {
                        System.out.println(copie.get(i));
                    }
                    break;
                }
                case "SORT_ASC": {
                    lista.sort(Comparator.comparingDouble(Tranzactie::getSuma));
                    for (Tranzactie t : lista) {
                        System.out.println(t);
                    }
                    break;
                }
                case "SORT_DESC": {
                    lista.sort((a, b) -> Double.compare(b.getSuma(), a.getSuma()));
                    for (Tranzactie t : lista) {
                        System.out.println(t);
                    }
                    break;
                }
                case "REVERSE": {
                    Collections.reverse(lista);
                    for (Tranzactie t : lista) {
                        System.out.println(t);
                    }
                    break;
                }
                case "MIN_MAX": {
                    Tranzactie min = Collections.min(lista, Comparator.comparingDouble(Tranzactie::getSuma));
                    Tranzactie max = Collections.max(lista, Comparator.comparingDouble(Tranzactie::getSuma));

                    System.out.println("MIN: " + min);
                    System.out.println("MAX: " + max);
                    break;
                }
                case "CME_DEMO": {
                    try {
                        for (Tranzactie t : lista) {
                            lista.remove(t);
                        }
                    } catch (ConcurrentModificationException e) {
                        System.out.println("ConcurrentModificationException prins: modificare in iteratie detectata.");
                    }
                    break;
                }
            }
        }
    }
}