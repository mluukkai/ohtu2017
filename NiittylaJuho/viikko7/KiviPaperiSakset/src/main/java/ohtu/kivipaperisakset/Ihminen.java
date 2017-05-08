package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Ihminen implements Pelaaja {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String annaSiirto() {
        return scanner.nextLine();
    }

    @Override
    public void asetaSiirto(String ekanSiirto) {
    }
}