/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

import java.util.Scanner;

/**
 *
 * @author ekalaja
 */
public class Peli {

//    private static final Scanner scanner = new Scanner(System.in);
    private Pelaaja pelaaja1;
    private Pelaaja pelaaja2;
    private Tuomari tuomari;

    public void pelaa(Pelaaja pelaaja1, Pelaaja pelaaja2) {
        this.tuomari = new Tuomari();
        this.pelaaja1 = pelaaja1;
        this.pelaaja2 = pelaaja2;
//        System.out.println("Tietokone valitsi: " + tokanSiirto);
        while (true) {
//            System.out.println("Pelaajan " + pelaaja1.toString() + " siirto: ");
            String ekanSiirto = pelaaja1.annaSiirto();
            System.out.println(pelaaja1.toString() + " valitsi siirron: " + ekanSiirto);
            if (!(onkoOkSiirto(ekanSiirto))) {
                break;
            }
//            System.out.println("Pelaajan " + pelaaja2.toString() + " siirto: ");
            String tokanSiirto = pelaaja2.annaSiirto();
            System.out.println(pelaaja2.toString() + " valitsi siirron: " + tokanSiirto);

            if (!(onkoOkSiirto(ekanSiirto))) {
                break;
            }
            pelaaja2.asetaSiirto(ekanSiirto);
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }

    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}
