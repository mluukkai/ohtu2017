
package ohtu;


public class Kauppa {
    private int yhteishinta;
    private Pankki pankki;
    private Viitegeneraattori viitegeneraattori;

    public Kauppa(Pankki pankki, Viitegeneraattori viitegeneraattori) {
        this.pankki = pankki;
        this.viitegeneraattori = viitegeneraattori;
    }


    public void aloitaOstokset(){
        yhteishinta = 0;
    }
    
    public void lisaaOstos(int hinta){
        yhteishinta += hinta;
    }
    
    public void maksa(String tilinumero){
        pankki.maksa(tilinumero, yhteishinta, viitegeneraattori.seruaava());
    }
}
