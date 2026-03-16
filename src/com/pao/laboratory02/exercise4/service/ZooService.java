package com.pao.laboratory02.exercise4.service;

import com.pao.laboratory02.exercise4.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class ZooService {

    private List<Animal> animals;

    // === Singleton (DAT — nu modifica) ===
    private ZooService() {
        this.animals = new ArrayList<>();
    }

    private static class Holder {
        private static final ZooService INSTANCE = new ZooService();
    }

    public static ZooService getInstance() {
        return Holder.INSTANCE;
    }
    // === Sfârșit Singleton ===


    public void addAnimal(Animal a) {
        // TODO: implementează aici
        animals.add(a);
        System.out.println("Adaugat: " + a);
    }


    public void listAll() {
        // TODO: implementează aici
        if (animals.isEmpty()) {
            System.out.println("Gradina zoologica este goala");
            return;
        }

        for (int i = 0; i < animals.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + animals.get(i).describe());
        }
    }


    public void listByType(String type) {
        // TODO: implementează aici
        boolean found = false;
        for (int i=0; i<animals.size(); i++) {
            Animal animal = animals.get(i);

            if (animal.getClass().getSimpleName().equals(type)) {
                System.out.println(animal.describe());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Nu există animale de tipul: " + type);
        }
    }


    public void findOldest() {
        // TODO: implementează aici

        if (animals.isEmpty()) {
            System.out.println("Gradina zoologica este goala");
            return;
        }
        Animal oldest = animals.get(0);

        for (int i=1; i<animals.size(); i++) {
            if (animals.get(i).getAge() > oldest.getAge()) {
                oldest = animals.get(i);
            }
        }
        System.out.println("Cel mai batran animal: " + oldest.describe());
    }
}