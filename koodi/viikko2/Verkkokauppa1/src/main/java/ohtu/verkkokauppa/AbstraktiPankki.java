package ohtu.verkkokauppa;

public interface AbstraktiPankki {
    boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);
}
