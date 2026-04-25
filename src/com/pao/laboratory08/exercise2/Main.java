package com.pao.laboratory08.exercise2;

import com.pao.laboratory08.exercise1.Student;
import com.pao.laboratory08.exercise1.Adresa;

import java.io.*;
import java.util.*;

public class Main {
    private static final String FILE_PATH = "src/com/pao/laboratory08/tests/studenti.txt";
    public static void main(String[] args) throws Exception {
        List<Student> studenti = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String linie;

        while ((linie = br.readLine()) != null) {
            if (linie.trim().isEmpty())
                continue;
            String[] parts = linie.split(",");

            String nume = parts[0].trim();
            int varsta = Integer.parseInt(parts[1].trim());
            String oras = parts[2].trim();
            String strada = parts[3].trim();

            studenti.add(new Student(nume, varsta, new Adresa(oras, strada)));
        }
        br.close();
        Scanner sc = new Scanner(System.in);
        int prag = sc.nextInt();
        List<Student> filtrati = new ArrayList<>();
        for (Student s : studenti) {
            if (s.getVarsta() >= prag) {
                filtrati.add(s);
            }
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter("rezultate.txt"));
        for (Student s : filtrati) {
            bw.write(s.toString());
            bw.newLine();
        }
        bw.close();
        System.out.println("Filtru: varsta >= " + prag);
        System.out.println("Rezultate: " + filtrati.size() + " studenti");
        System.out.println();
        for (Student s : filtrati) {
            System.out.println(s);
        }
        System.out.println();
        System.out.println("Scris in: rezultate.txt");
    }
}