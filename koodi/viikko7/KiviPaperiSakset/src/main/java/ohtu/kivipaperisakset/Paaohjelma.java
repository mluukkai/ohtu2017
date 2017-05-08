package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Pelaaja yksi = null;
        Pelaaja kaksi = null;
        
        while (true) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String vastaus = scanner.nextLine();
            if (vastaus.endsWith("a")) {
                yksi = new Ihminen("pelaaja1");
                kaksi = new Ihminen("pelaaja2");

            } else if (vastaus.endsWith("b")) {
                yksi = new Ihminen("pelaaja1");
                kaksi = new Tekoaly("tekoäly");
            } else if (vastaus.endsWith("c")) {
                yksi = new Ihminen("pelaaja1");
                kaksi = new TekoalyParannettu("hurja tekoäly", 20);
            }
             else {
                break;
            }
            System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
            Peli peli = new Peli();
            peli.pelaa(yksi, kaksi);

        }

    }
}
