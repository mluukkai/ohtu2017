
import java.util.*;
import ohtu.Multiplier;

public class Main {

    public static final int MAX_MULTIPLIER_SIZE = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Multiplier kolme = new Multiplier(MAX_MULTIPLIER_SIZE);
        System.out.println("anna luku ");
        int luku = scanner.nextInt();

        System.out.println("luku kertaa kolme on " + kolme.multipliedBy(luku));
    }
}
