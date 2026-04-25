package com.pao.laboratory08.exercise1;

import java.io.*;
import java.util.*;

public class Main {
    private static final String FILE_PATH = "src/com/pao/laboratory08/tests/studenti.txt";

    public static void main(String[] args) {
        List<Student> studenti = new ArrayList<>();
        try {
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

                Adresa adresa = new Adresa(oras, strada);
                Student s = new Student(nume, varsta, adresa);

                studenti.add(s);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        String[] cmd = line.split(" ", 2);
        String operatie = cmd[0];
        String nume = cmd.length > 1 ? cmd[1] : "";
        try {
            if (operatie.equals("PRINT")) {
                for (Student s : studenti) {
                    System.out.println(s);
                }
            }
            else if (operatie.equals("SHALLOW")) {
                for (Student s : studenti) {
                    if (s.getNume().equals(nume)) {
                        Student clona = s.shallowClone();
                        clona.getAdresa().setOras("MODIFICAT");

                        System.out.println("Original: " + s);
                        System.out.println("Clona: " + clona);
                        break;
                    }
                }
            }
            else if (operatie.equals("DEEP")) {
                for (Student s : studenti) {
                    if (s.getNume().equals(nume)) {

                        Student clona = s.deepClone();
                        clona.getAdresa().setOras("MODIFICAT");

                        System.out.println("Original: " + s);
                        System.out.println("Clona: " + clona);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}