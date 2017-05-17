package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
public class Kauppa {

    private VarastoInterface varastoIf;
    private PankkiInterface pankkiIf;
    private Ostoskori ostoskori;
    private ViitegeneraattoriInterface viitegeneraattoriIf;
    private String kaupanTili;

    public Kauppa(VarastoInterface varasto, PankkiInterface pankki, ViitegeneraattoriInterface viitegeneraattori) {
        this.varastoIf = varasto;
        this.pankkiIf = pankki;
        this.viitegeneraattoriIf = (Viitegeneraattori) viitegeneraattori;
        kaupanTili = "33333-44455";

    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varastoIf.haeTuote(id);
        varastoIf.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (varastoIf.saldo(id) > 0) {
            Tuote t = varastoIf.haeTuote(id);
            ostoskori.lisaa(t);
            varastoIf.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattoriIf.uusi();
        int summa = ostoskori.hinta();

        return pankkiIf.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
