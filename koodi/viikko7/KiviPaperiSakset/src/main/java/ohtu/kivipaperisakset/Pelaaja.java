/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

/**
 *
 * @author ekalaja
 */
public abstract class Pelaaja {
    
    

    private String siirto;
    private String nimi;
    
    public Pelaaja(String nimi) {
        this.nimi = nimi;
    }
    
    public String annaSiirto() {
        return siirto;
    }
    
    public void asetaSiirto(String siirto) {
        
    }
    
    @Override
    public String toString() {
        return this.nimi;
    }

}
