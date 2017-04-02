import java.util.*;
import ohtu.Multiplier;

public class Main {

    static final int mul = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Multiplier kolme = new Multiplier(mul);
        System.out.println("anna luku ");
        int luku = scanner.nextInt();

        System.out.println("luku kertaa kolme on "+kolme.multipliedBy(luku) );
    }
}
