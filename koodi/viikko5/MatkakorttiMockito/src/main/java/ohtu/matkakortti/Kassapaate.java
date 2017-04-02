package ohtu.matkakortti;

public class Kassapaate {
    private int myytyjaLounaita;
    public static final int HINTA = 5;

    public Kassapaate() {
        this.myytyjaLounaita = 0;
    }
    
    public void lataa(Matkakortti kortti, int summa){
        kortti.lataa(summa);
    }
    
    public void ostaLounas(Matkakortti kortti) {
        kortti.osta(HINTA);
        myytyjaLounaita++;
    }

    public int getMyytyjaLounaita() {
        return myytyjaLounaita;
    }
}
