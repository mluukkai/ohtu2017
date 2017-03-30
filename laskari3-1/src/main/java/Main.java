import java.util.*;
import ohtu.Multiplier;

public class Main {
    private static final int KOLME = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Multiplier kolme = new Multiplier(KOLME);
        System.out.println("anna luku ");
        int luku = scanner.nextInt();

        System.out.println("luku kertaa kolme on "+kolme.multipliedBy(luku) );

        if(true){
            if(false){
                System.out.println("Moi");
            }
        }

    }
}
