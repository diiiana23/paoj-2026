package com.pao.laboratory04.audit;

import java.util.Scanner;
/**
 * Exercise 4 (Bonus) — Audit Log
 *
 * Cerințele complete se află în:
 *   src/com/pao/laboratory05/Readme.md  →  secțiunea "Exercise 4 (Bonus) — Audit"
 *
 * Extinde soluția de la Exercise 3 cu un sistem de audit bazat pe record.
 * Creează fișierele de la zero în acest pachet, apoi rulează Main.java
 * pentru a verifica output-ul așteptat din Readme.
 */
public class Main {
    public static void main(String[] args) {
        AngajatService service = AngajatService.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Gestionare Angajați (cu Audit) =====");
            System.out.println("1. Adaugă angajat");
            System.out.println("3. Caută după departament");
            System.out.println("4. Afișează audit log");
            System.out.println("0. Ieșire");
            System.out.print("Opțiune: ");

            int opt = scanner.nextInt();
            scanner.nextLine();

            if (opt == 0) break;
            if (opt == 1) {
                System.out.print("Nume: "); String nume = scanner.nextLine();
                System.out.print("Dept: "); String dept = scanner.nextLine();
                service.addAngajat(new Angajat(nume, new Departament(dept, "Locatie"), 5000));
            } else if (opt == 3) {
                System.out.print("Dept: "); String dept = scanner.nextLine();
                service.findByDepartament(dept);
            } else if (opt == 4) {
                service.printAuditLog();
            }
        }
        scanner.close();
    }
}