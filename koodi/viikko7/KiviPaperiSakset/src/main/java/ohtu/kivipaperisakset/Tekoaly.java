package ohtu.kivipaperisakset;

public class Tekoaly {

    int siirto;

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

    void asetaSiirto(String ekanSiirto) {
        // ei tehdä mitään 
    }
}
