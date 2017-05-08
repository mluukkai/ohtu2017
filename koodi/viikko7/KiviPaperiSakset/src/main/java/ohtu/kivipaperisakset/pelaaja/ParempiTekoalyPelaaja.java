/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset.pelaaja;

/**
 *
 * @author jarkko
 */
public class ParempiTekoalyPelaaja extends Pelaaja {

  private String[] muisti;
  private int vapaaMuistiIndeksi;

  public ParempiTekoalyPelaaja(int muistinKoko) {
    muisti = new String[muistinKoko];
    vapaaMuistiIndeksi = 0;
  }
  
  public ParempiTekoalyPelaaja(){
      this(20);
  }
  
  @Override
  public void asetaSiirto(String siirto) {
    // jos muisti täyttyy, unohdetaan viimeinen alkio
    if(vapaaMuistiIndeksi == muisti.length) {
      for(int i = 1; i < muisti.length; i++) {
        muisti[i-1] = muisti[i];
      }
      
      vapaaMuistiIndeksi--;
    }
    
    muisti[vapaaMuistiIndeksi] = siirto;    
    vapaaMuistiIndeksi++;
  }

  
  @Override
  public String annaSiirto() {
    String viimeisinSiirto = muisti[vapaaMuistiIndeksi - 1];
    
    int k = 0;
    int p = 0;
    int s = 0;
    
    
    for(int i = 0; i < vapaaMuistiIndeksi - 1; i++) {
      if(viimeisinSiirto.equals(muisti[i])) {
        String seuraava = muisti[i+1];
        
        if("k".equals(seuraava)) {
          k++;
        }
        else if("p".equals(seuraava)) {
          p++;
        }
        else {
          s++;
        }        
      }
    }
    
    
    // Tehdään siirron valinta esimerkiksi seuraavasti;
    // - jos kiviä eniten, annetaan aina paperi
    // - jos papereita eniten, annetaan aina sakset
    // muulloin annetaan aina kivi
    if(k > p && k > s) {
      return "p";
    }
    else if (p > k && p > s) {
      return "s";
    }
    else {
      return "k";
    }
    
    // Tehokkaampiakin tapoja löytyy, mutta niistä lisää 
    // Johdatus Tekoälyyn kurssilla!
  }
}
