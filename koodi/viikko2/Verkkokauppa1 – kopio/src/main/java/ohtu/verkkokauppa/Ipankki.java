
package ohtu.verkkokauppa;

public interface Ipankki {

    boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);
    
}
