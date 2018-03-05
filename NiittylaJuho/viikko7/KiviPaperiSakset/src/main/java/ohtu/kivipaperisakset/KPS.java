package ohtu.kivipaperisakset;

public interface KPS {
    void pelaa();

   default boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}
