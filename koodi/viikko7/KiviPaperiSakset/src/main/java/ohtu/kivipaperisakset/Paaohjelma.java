package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String vastaus = scanner.nextLine();
            Peli peli;
            if (vastaus.endsWith("a")) {
                peli = Peli.kaksinpeli(scanner);
            } else if (vastaus.endsWith("b")) {
                peli = Peli.helppoYksinpeli(scanner);
            } else if (vastaus.endsWith("c")) {
                peli = Peli.vaikeaYksinpeli(scanner);
            } else {
                break;
            }

            System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
            peli.pelaa();
        }

    }
}
