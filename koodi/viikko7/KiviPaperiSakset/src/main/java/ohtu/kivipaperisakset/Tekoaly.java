package ohtu.kivipaperisakset;

public class Tekoaly extends Pelaaja{

    int siirto;

    public Tekoaly(String nimi) {
        super(nimi);
    }

    public void Tekoaly() {
        siirto = 0;
    }

    @Override
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

//    void asetaSiirto(String ekanSiirto) {
//        // ei tehdä mitään 
//    }
}
