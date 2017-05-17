/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset.pelaaja;

import java.util.Scanner;

/**
 *
 * @author jarkko
 */
public class IhmisPelaaja extends Pelaaja  {
   
    
    private Scanner syote;
    
    public IhmisPelaaja(Scanner s){
        syote = s;
    }

    @Override
    public String annaSiirto() {
        System.out.print("anna komento: ");
        return syote.nextLine();
    }
    
}
