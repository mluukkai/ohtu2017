package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Ihmispelaaja implements Pelaaja {
    private final Scanner scanner;

    public Ihmispelaaja(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String annaSiirto() {
        return scanner.nextLine();
    }

    @Override
    public void kerroSiirto(String toisenSiirto) { }

    @Override
    public boolean onIhminen() {
        return true;
    }
}
