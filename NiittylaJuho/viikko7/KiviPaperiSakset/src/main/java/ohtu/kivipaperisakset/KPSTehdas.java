package ohtu.kivipaperisakset;

public class KPSTehdas {

    private KPSTehdas() {
    }

    public static KPS newKPS(char type) {
        switch (type) {
            case 'a':
                return new KPSPeli(new Ihminen(), new Ihminen(), false);
            case 'b':
                return new KPSPeli(new Ihminen(), new Tekoaly(), true);
            case 'c':
                return new KPSPeli(new Ihminen(), new TekoalyParannettu(20), true);
            default:
                break;
        }
        return null;
    }
}