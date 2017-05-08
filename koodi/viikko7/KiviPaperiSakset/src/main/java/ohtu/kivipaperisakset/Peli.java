/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

import java.util.Scanner;
import ohtu.kivipaperisakset.pelaaja.Pelaaja;
import ohtu.kivipaperisakset.Paaohjelma;
import ohtu.kivipaperisakset.pelaaja.IhmisPelaaja;
import ohtu.kivipaperisakset.pelaaja.ParempiTekoalyPelaaja;
import ohtu.kivipaperisakset.pelaaja.TekoalyPelaaja;

/**
 *
 * @author jarkko
 */
public class Peli {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static Peli PVP(){
        return new Peli(new IhmisPelaaja(scanner), new IhmisPelaaja(scanner));
    }
    
    public static Peli PVT(){
        return new Peli(new IhmisPelaaja(scanner), new TekoalyPelaaja());
    }
    
    public static Peli PVHT(){
        return new Peli(new IhmisPelaaja(scanner), new ParempiTekoalyPelaaja());
    }
    
    
    private Pelaaja p1, p2;
    private Tuomari t;
    
    public Peli(Pelaaja p1, Pelaaja p2){
        this.p1 = p1;
        this.p2 = p2;
        this.t = new Tuomari();
    }
    
    public void pelaa(){
        System.out.print("Ensimmäisen pelaajan siirto: ");
        String ekanSiirto = scanner.nextLine();
        System.out.print("Toisen pelaajan siirto: ");
        String tokanSiirto = scanner.nextLine();

        while (t.onkoOkSiirto(ekanSiirto) && t.onkoOkSiirto(tokanSiirto)) {
            t.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(t);
            System.out.println();

            System.out.print("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = p1.annaSiirto();
            p2.asetaSiirto(ekanSiirto);
            
            System.out.print("Toisen pelaajan siirto: ");
            tokanSiirto = p2.annaSiirto();
            p1.asetaSiirto(tokanSiirto);
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(t);
    }
    
    
}
