package ohtu.kivipaperisakset;

public class Tekoaly implements Pelaaja {

    private int siirto;

    public Tekoaly() {
        siirto = 0;
    }

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

    @Override
    public void kerroSiirto(String toisenSiirto) { }

    @Override
    public boolean onIhminen() {
        return false;
    }
}
