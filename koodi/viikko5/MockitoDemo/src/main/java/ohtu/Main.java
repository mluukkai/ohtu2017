package ohtu;

public class Main {

    public static void main(String[] args) {
        Pankki myNetBank = new Pankki();
        Viitegeneraattori viitteet = new Viitegeneraattori();
        Kauppa kumpulaBiershop = new Kauppa(myNetBank, viitteet);

        kumpulaBiershop.aloitaOstokset();
        kumpulaBiershop.lisaaOstos(10);
        kumpulaBiershop.lisaaOstos(7);
        kumpulaBiershop.maksa("1234-1234");

        kumpulaBiershop.aloitaOstokset();
        kumpulaBiershop.lisaaOstos(1);
        kumpulaBiershop.lisaaOstos(1);
        kumpulaBiershop.lisaaOstos(2);
        kumpulaBiershop.lisaaOstos(2);
        kumpulaBiershop.maksa("4444-1111");
    }
}
