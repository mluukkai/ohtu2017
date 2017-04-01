package ohtu;

public class Multiplier {

    private int value;
    public static final int ekaa = 10;

    public Multiplier(int value) {
        this.value = value;
    }

    public int multipliedBy(int other) {
        return value * other;
    }

    public int pitka() {
        int eka = 1;
        int toka = 2;
        if (eka < toka) {
            eka = toka;
        }
        for (int i = 0; i < ekaa; i++) {
            System.out.println("jee");
        }
        return 0;

    }
}
