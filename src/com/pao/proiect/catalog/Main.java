package com.pao.proiect.catalog;

import com.pao.proiect.catalog.model.*;
import com.pao.proiect.catalog.service.*;
import com.pao.proiect.catalog.exception.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StudentService studentService = StudentService.getInstance();
        ProfesorService profesorService = ProfesorService.getInstance();
        NotaService notaService = NotaService.getInstance();

        //date initiale
        Materie m1 = new Materie("Matematica");
        Materie m2 = new Materie("Romana");
        Materie m3 = new Materie("Informatica");

        Profesor p1 = new Profesor(1, "Radulescu Vlad", "Matematica");
        Profesor p2 = new Profesor(2, "Ionescu Cristina", "Romana");
        Profesor p3 = new Profesor(3, "Stefanescu Tudor", "Informatica");
        profesorService.adaugaProfesor(p1);
        profesorService.adaugaProfesor(p2);
        profesorService.adaugaProfesor(p3);

        Student s1 = new Student(1, "Radoiu Razvan");
        Student s2 = new Student(2, "Ciubotaru Vlad");
        Student s3 = new Student(3, "Stancu Maria");
        studentService.adaugaStudent(s1);
        studentService.adaugaStudent(s2);
        studentService.adaugaStudent(s3);

        notaService.adaugaNota(s1, 10, m1);
        notaService.adaugaNota(s1, 9, m2);
        notaService.adaugaNota(s1, 5, m3);
        notaService.adaugaNota(s2, 7, m1);
        notaService.adaugaNota(s2, 2, m2);
        notaService.adaugaNota(s2, 10, m3);
        notaService.adaugaNota(s3, 9, m1);
        notaService.adaugaNota(s3, 8, m2);
        notaService.adaugaNota(s3, 9, m3);


        while (true)
        {
            System.out.println("\n MENIU:");
            System.out.println("1. Afiseaza studenti");
            System.out.println("2. Afiseaza profesori");
            System.out.println("3. Afiseaza note (pt un anumit student)");
            System.out.println("4. Adauga student");
            System.out.println("5. Adauga profesor");
            System.out.println("6. Adauga nota (pt un anumit student & la o anumita materie)");
            System.out.println("7. Calculeaza media (pt un student / pt toti)");
            System.out.println("8. Cautare student");
            System.out.println("9. Sortare studenti");
            System.out.println("10. Stergere student");
            System.out.println("0. Iesire");

            int optiune = scanner.nextInt();
            scanner.nextLine();
            switch (optiune)
            {
                case 1: {
                    studentService.getTotiStudentii().forEach(System.out::println);
                    break;
                }
                case 2: {
                    profesorService.getTotiProfesorii().forEach(System.out::println);
                    break;
                }
                case 3: {
                    try {
                        System.out.print("ID student: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        Student s = studentService.getStudent(id);
                        s.getNote().forEach(System.out::println);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 4: {
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nume: ");
                    String nume = scanner.nextLine();
                    studentService.adaugaStudent(new Student(id, nume));
                    break;
                }
                case 5: {
                    System.out.print("ID profesor: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nume profesor: ");
                    String nume = scanner.nextLine();
                    System.out.print("Materie: ");
                    String materie = scanner.nextLine();
                    profesorService.adaugaProfesor(new Profesor(id, nume, materie));
                    break;
                }
                case 6: {
                    try {
                        System.out.print("ID student: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        Student s = studentService.getStudent(id);

                        System.out.println("Alege materia: 1.Mate 2.Romana 3.Info");
                        int opt = scanner.nextInt();
                        scanner.nextLine();
                        Materie materie = (opt == 1)?m1 : (opt == 2)?m2 : m3;
                        if(opt<1 || opt>3) {
                            System.out.println("Optiune invalida!");
                            break;
                        }

                        System.out.print("Nota: ");
                        int val = scanner.nextInt();
                        scanner.nextLine();

                        notaService.adaugaNota(s, val, materie);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 7: {
                    System.out.println("1. Un student");
                    System.out.println("2. Toti studentii");
                    int opt = scanner.nextInt();
                    scanner.nextLine();
                    if(opt == 1){
                        try {
                            System.out.print("ID student: ");
                            int idStudent = scanner.nextInt();
                            scanner.nextLine();
                            Student s = studentService.getStudent(idStudent);

                            System.out.println("Media: " + s.calculeazaMedia());
                        } catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    } else{
                        studentService.getTotiStudentii()
                                .forEach(st -> System.out.println(st.getNume() + " are media: " + st.calculeazaMedia()));

                    }
                    break;
                }
                case 8: {
                    try {
                        System.out.print("Nume student: ");
                        String nume = scanner.nextLine();
                        Student s = studentService.cautaDupaNume(nume);
                        System.out.println(s);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 9: {
                    List<Student> lista = studentService.getTotiStudentii();
                    Collections.sort(lista);
                    lista.forEach(System.out::println);
                    break;
                }
                case 10: {
                    try {
                        System.out.print("ID student: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        studentService.stergeStudent(id);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 0:
                    return;
                default:
                    System.out.println("Optiune invalida!");
            }
        }
    }
}