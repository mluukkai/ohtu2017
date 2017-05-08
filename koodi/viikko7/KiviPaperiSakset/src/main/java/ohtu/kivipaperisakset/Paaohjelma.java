package ohtu.kivipaperisakset;

import java.util.Scanner;
import ohtu.kivipaperisakset.pelaaja.IhmisPelaaja;
import ohtu.kivipaperisakset.pelaaja.ParempiTekoalyPelaaja;
import ohtu.kivipaperisakset.pelaaja.Pelaaja;
import ohtu.kivipaperisakset.pelaaja.TekoalyPelaaja;

public class Paaohjelma {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");
            Pelaaja vastustaja;

            String vastaus = scanner.nextLine();
            if (vastaus.endsWith("a")) {
                vastustaja = new IhmisPelaaja(scanner);
            } else if (vastaus.endsWith("b")) {
                vastustaja = new TekoalyPelaaja();
            } else if (vastaus.endsWith("c")) {
                vastustaja = new ParempiTekoalyPelaaja();
            } else {
                return;
            }
            
            System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
            new Peli(new IhmisPelaaja(scanner), vastustaja).pelaa();

        }

    }
}
