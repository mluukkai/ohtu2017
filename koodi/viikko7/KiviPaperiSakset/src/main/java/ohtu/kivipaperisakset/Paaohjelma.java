package ohtu.kivipaperisakset;

import ohtu.kivipaperisakset.pelaajat.HuonoTekoAly;
import ohtu.kivipaperisakset.pelaajat.HyvaTekoaly;
import ohtu.kivipaperisakset.pelaajat.Ihmispelaaja;

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
            if(vastaus.equals("a") || vastaus.equals("b") || vastaus.equals("c")) {
                System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
                switch (vastaus) {
                    case "a":
                        new Pelilogiikka().pelaa(new Ihmispelaaja("Spede Pasanen"), new Ihmispelaaja("Luke Skywalker"));
                        break;
                    case "b":
                        new Pelilogiikka().pelaa(new HuonoTekoAly());
                        break;
                    case "c":
                        new Pelilogiikka().pelaa(new HyvaTekoaly(20));
                        break;
                }
            } else return;
        }

    }
}
