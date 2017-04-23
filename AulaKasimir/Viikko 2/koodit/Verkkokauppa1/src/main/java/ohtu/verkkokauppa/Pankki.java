package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class Pankki implements PankkiInterface {    
    
    private Kirjanpito kirjanpito;
    
    public Pankki(KirjanpitoInterface kI) {
        kirjanpito = (Kirjanpito) kI;
    }
    
    public Kirjanpito getKirjanpito() {
        return this.kirjanpito;
    }

    @Override
    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        kirjanpito.lisaaTapahtuma("tilisiirto: tililtä " + tilille + " tilille " + tilille
                + " viite " + viitenumero + " summa " + summa + "e");

        // täällä olisi koodi joka ottaa yhteyden pankin verkkorajapintaan
        return true;
    }
}
