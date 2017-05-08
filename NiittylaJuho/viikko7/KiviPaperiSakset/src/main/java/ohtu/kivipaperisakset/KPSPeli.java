package ohtu.kivipaperisakset;

public class KPSPeli implements KPS {

    private final Tuomari tuomari;
    private final Pelaaja eka, toka;
    private final boolean ai;

    public KPSPeli(Pelaaja eka, Pelaaja toka, boolean ai) {
        this.eka = eka;
        this.toka = toka;
        this.tuomari = new Tuomari();
        this.ai = ai;
    }

    @Override
    public void pelaa() {
        System.out.print("Ensimmäisen pelaajan siirto: ");
        String ekanSiirto = eka.annaSiirto();

        if (!ai) {
            System.out.print("Toisen pelaajan siirto: ");
        }

        String tokanSiirto = toka.annaSiirto();

        if (ai) {
            System.out.println("Tietokone valitsi: " + tokanSiirto);
        }

        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

            System.out.print("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = eka.annaSiirto();

            if (!ai) {
                System.out.print("Toisen pelaajan siirto: ");
            }

            tokanSiirto = toka.annaSiirto();

            if (ai) {
                System.out.println("Tietokone valitsi: " + tokanSiirto);
            }

            toka.asetaSiirto(ekanSiirto);
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }
}