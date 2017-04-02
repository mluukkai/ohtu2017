import java.util.Scanner;
import ohtu.Multiplier;

/**
 * hyvää dokumentaatiota.
 */
public final class Main {

    /**
    * privaatti konstruktööri.
    */
    private Main() {
          //not called
    }
  /**
   * hyvää dokumentaatiota.
   @param args argumentteja kiitos.
   */
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        Multiplier kolme = new Multiplier('3');
        System.out.println("anna luku ");
        int luku = scanner.nextInt();

        System.out.println("luku kertaa kolme on " + kolme.multipliedBy(luku));
    }
}
