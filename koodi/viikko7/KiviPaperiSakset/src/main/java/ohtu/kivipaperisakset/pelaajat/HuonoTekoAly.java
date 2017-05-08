package ohtu.kivipaperisakset.pelaajat;

public class HuonoTekoAly implements Pelaaja {

    int siirto;

    public HuonoTekoAly() {
        siirto = 0;
    }

    public void asetaMuisti(int muistinKoko) {  }

    public String annaSiirto() {
        siirto++;
        siirto = siirto % 3;

        if (siirto == 0) {
            return "k";
        } else if (siirto == 1) {
            return "p";
        } else {
            return "s";
        }
    }

    public void asetaSiirto(String ekanSiirto) {
        // ei tehdä mitään 
    }

    public String getNimi() {
        return "Tekoäly";
    }
}
