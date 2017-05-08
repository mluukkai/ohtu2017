package ohtu.kivipaperisakset;

// Tuomari pitää kirjaa ensimmäisen ja toisen pelaajan pisteistä sekä tasapelien määrästä.
public class Tuomari {

    private int p1pisteet;
    private int p2pisteet;
    private int tasapelit;

    public Tuomari() {
        this.p1pisteet = 0;
        this.p2pisteet = 0;
        this.tasapelit = 0;
    }

    public void kirjaaSiirto(String ekanSiirto, String tokanSiirto) {
        if (tasapeli(ekanSiirto, tokanSiirto)) {
            tasapelit++;
        } else if (p1voittaa(ekanSiirto, tokanSiirto)) {
            p1pisteet++;
        } else {
            p2pisteet++;
        }
    }

    // sisäinen metodi, jolla tarkastetaan tuliko tasapeli
    private static boolean tasapeli(String ekanSiirto, String tokanSiirto) {
        if (ekanSiirto.equals(tokanSiirto)) {
            return true;
        }

        return false;
    }

    // sisäinen metodi joka tarkastaa voittaako eka pelaaja tokan
    private static boolean p1voittaa(String ekanSiirto, String tokanSiirto) {
        if ("k".equals(ekanSiirto) && "s".equals(tokanSiirto)) {
            return true;
        } else if ("s".equals(ekanSiirto) && "p".equals(tokanSiirto)) {
            return true;
        } else if ("p".equals(ekanSiirto) && "k".equals(tokanSiirto)) {
            return true;
        }

        return false;
    }

    public String getTilanne() {
        String s = "Pelitilanne: " + p1pisteet + " - " + p2pisteet + "\n"
                + "Tasapelit: " + tasapelit;
        return s;
    }
}