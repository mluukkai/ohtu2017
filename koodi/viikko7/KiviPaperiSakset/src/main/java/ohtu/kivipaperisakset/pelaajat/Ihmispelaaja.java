package ohtu.kivipaperisakset.pelaajat;

import java.util.Scanner;

/**
 * Created by migho on 8.5.2017.
 */
public class Ihmispelaaja implements Pelaaja {

    private String nimi;
    private static final Scanner scanner = new Scanner(System.in);

    public Ihmispelaaja(String nimi) {
        this.nimi = nimi;
    }

    public Ihmispelaaja() {
        this("Pelaaja");
    }

    public void asetaMuisti(int muistinKoko) {}

    public String annaSiirto() {
        return scanner.nextLine();
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getNimi() {
        return nimi;
    }

    public void asetaSiirto(String ekaSiirto) {}
}
