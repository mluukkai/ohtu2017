package ohtu.verkkokauppa;

/**
 * Created by Paavo on 22.3.2017.
 */
public interface AbstraktiPankki {
    boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);
}
