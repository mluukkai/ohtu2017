package ohtu.kivipaperisakset.pelaajat;

/**
 * Created by migho on 8.5.2017.
 */
public interface Pelaaja {

    void asetaMuisti(int muistinKoko);
    String annaSiirto();
    void asetaSiirto(String ekaSiirto);
    String getNimi();

}
