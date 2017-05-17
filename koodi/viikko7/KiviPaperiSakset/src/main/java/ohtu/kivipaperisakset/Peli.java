package ohtu.kivipaperisakset;

import java.util.Scanner;

class Peli {
    private final Tuomari tuomari = new Tuomari();
    private final Pelaaja pelaaja1;
    private final Pelaaja pelaaja2;

    private Peli(Pelaaja pelaaja1, Pelaaja pelaaja2) {
        this.pelaaja1 = pelaaja1;
        this.pelaaja2 = pelaaja2;
    }

    void pelaa() {
        while (true) {
            String ekanSiirto = kasitteleSiirto(pelaaja1);
            if (ekanSiirto == null) break;
            String tokanSiirto = kasitteleSiirto(pelaaja2);
            if (tokanSiirto == null) break;

            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

            pelaaja1.kerroSiirto(tokanSiirto);
            pelaaja2.kerroSiirto(ekanSiirto);
        }
    }

    private String kasitteleSiirto(Pelaaja pelaaja) {
        if (pelaaja == pelaaja1) {
            System.out.print("Ensimmäisen pelaajan siirto: ");
        } else {
            System.out.print("Toisen pelaajan siirto: ");
        }

        String siirto = pelaaja.annaSiirto();

        if (!onkoOkSiirto(siirto)) return null;

        if (!pelaaja.onIhminen()) {
            System.out.println(siirto);
        }

        return siirto;
    }

    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

    static Peli kaksinpeli(Scanner scanner) {
        return new Peli(new Ihmispelaaja(scanner), new Ihmispelaaja(scanner));
    }

    static Peli helppoYksinpeli(Scanner scanner) {
        return new Peli(new Ihmispelaaja(scanner), new Tekoaly());
    }

    static Peli vaikeaYksinpeli(Scanner scanner) {
        return new Peli(new Ihmispelaaja(scanner), new TekoalyParannettu(20));
    }
}
