package ohtu.kivipaperisakset;

import ohtu.kivipaperisakset.pelaajat.Ihmispelaaja;
import ohtu.kivipaperisakset.pelaajat.Pelaaja;

import java.util.Scanner;

public class Pelilogiikka {

    public void pelaa(Pelaaja p1, Pelaaja p2) {
        Tuomari tuomari = new Tuomari();
        String ekanSiirto;
        String tokanSiirto;

        while (true) {
            System.out.println(tuomari.getTilanne() + "\n");

            ekanSiirto = teeKierros(p1);
            if(ekanSiirto == null) break;
            tokanSiirto = teeKierros(p2);
            if(tokanSiirto == null) break;

            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
        }

        System.out.println("\nKiitos!");
        System.out.println(tuomari.getTilanne());
    }

    public void pelaa(Pelaaja p1) {
        this.pelaa(new Ihmispelaaja(), p1);
    }

    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

    private static String teeKierros(Pelaaja pelaaja) {
        System.out.print("Pelaajan " + pelaaja.getNimi() + " siirto: ");
        String siirto = pelaaja.annaSiirto();
        if(!onkoOkSiirto(siirto)) return null;
        System.out.println(pelaaja.getNimi() + " valitsi " + siirto);
        pelaaja.asetaSiirto(siirto);
        return siirto;
    }
}