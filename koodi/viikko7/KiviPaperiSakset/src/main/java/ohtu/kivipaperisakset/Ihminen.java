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
public class Ihminen extends Pelaaja {

    private static final Scanner scanner = new Scanner(System.in);

    public Ihminen(String nimi) {
        super(nimi);
    }

    @Override
    public String annaSiirto() {       
            String ekanSiirto = scanner.nextLine();
            return ekanSiirto;
    }

}
