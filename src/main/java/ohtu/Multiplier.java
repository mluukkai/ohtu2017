package ohtu;

/**
* Luokka Multiplier.
*/
public final class Multiplier {

      /**
      * luku.
      * @param value Hyvä luku tähän nyt.
      */
    private final int value;

    /**
    * Luokka Multiplier.
    * @param luku Hyvä luku tähän nyt.
    */
    public Multiplier(final int luku) {
        this.value = luku;
    }

        /**
        * funktio.
        * @param other Toinen hyvä luku tähän nyt.
        * @return int kerrottu luku.
        */
    public int multipliedBy(final int other) {
        return value * other;
    }

}
