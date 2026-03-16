package com.pao.laboratory01.comparators;

// EXERCITIU 1: cream in pachetul comparators o clasa Podcast cu durata (secunde, int) si titlu (string)
// dupa modelul AudioBook.java si Book.java, implementati:
// 1. toString — pentru afisare frumoasa
// 2. Comparable<Podcast> cu compareTo — sortare dupa titlu
// 3. un Comparator extern (PodcastLengthComparator) — sortare dupa durata
// 4. o metoda main in care cream cateva podcast-uri si le sortam in ambele moduri

public class Podcast implements Comparable<Podcast> {

    // TODO: adauga atributele: title (String), durationInSeconds (int)
    private String title;
    private int durationInSeconds;

    // TODO: adauga constructor cu ambele atribute
    public Podcast(String title, int durationInSeconds) {
        this.title = title;
        this.durationInSeconds = durationInSeconds;
    }

    // TODO: suprascrie toString()
    @Override
    public String toString() {
        return "Podcast{" +
                "title='" + title + '\'' +
                ", durationInSeconds=" + durationInSeconds +
                '}';
    }

    // TODO: implementeaza Comparable<Podcast> si suprascrie compareTo (dupa titlu)
    @Override
    public int compareTo(Podcast other) {
        return this.title.compareTo(other.title);
    }

    // TODO: adauga getter pentru durationInSeconds (necesar pentru comparator)
    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    // Metoda main — codul final care trebuie sa functioneze dupa implementare.
    // Ruleaza-l ca sa verifici ca totul e corect!
    public static void main(String[] args) {
        // cream cateva podcast-uri
        Podcast[] podcasts = {
                new Podcast("Tech Talk", 2400),
                new Podcast("Arta Conversatiei", 3600),
                new Podcast("Mindset", 1800)
        };

        //sortare naturala (compareTo) dupa titlu
        java.util.Arrays.sort(podcasts);
        System.out.println("sortate dupa titlu:");
        System.out.println(java.util.Arrays.toString(podcasts));

        //sortare cu Comparator extern dupa durata
        java.util.Arrays.sort(podcasts, new PodcastLengthComparator());
        System.out.println("sortate dupa durata (crescator):");
        System.out.println(java.util.Arrays.toString(podcasts));

        //sortare cu lambda (dupa durata descrec)
        java.util.Arrays.sort(podcasts,
                (p1, p2) -> Integer.compare(p2.getDurationInSeconds(), p1.getDurationInSeconds())
        );
        System.out.println("sortate dupa durata:");
        System.out.println(java.util.Arrays.toString(podcasts));
    }
}

// TODO: creeaza o clasa PodcastLengthComparator care implementeaza Comparator<Podcast>
//  si compara dupa durationInSeconds (vezi AudioBookLengthComparator ca model)

class PodcastLengthComparator implements java.util.Comparator<Podcast> {
    @Override
    public int compare(Podcast p1, Podcast p2) {
        return Integer.compare(p1.getDurationInSeconds(), p2.getDurationInSeconds() );
    }
}