/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset.pelaaja;

import java.util.Random;

/**
 *
 * @author jarkko
 */
public class TekoalyPelaaja extends Pelaaja  {
    private final Random rand;

    public TekoalyPelaaja() {
        rand = new Random();
    }
    
    @Override
    public String annaSiirto() {
        switch(rand.nextInt() % 3){
            case 0: return "k";
            case 1: return "p";
            case 2: return "s";
            
            default: return "haloo";
        }
    }
    
}
