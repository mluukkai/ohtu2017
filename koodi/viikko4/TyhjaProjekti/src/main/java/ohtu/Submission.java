package ohtu;

public class Submission {
    public String student_number;
    private int week;
    public int hours;
    public  int points;
    private boolean a1;
    private boolean a2;
    private boolean a3;
    private boolean a4;
    private boolean a5;
    private boolean a6;
    private boolean a7;
    private boolean a8;
    private boolean a9;
    private boolean a10;
    private boolean a11;
    private boolean a12;
    private boolean a13;
    private boolean a14;
    private boolean a15;
    private boolean a16;
    private boolean a17;
    private boolean a18;
    private boolean a19;
    private boolean a20;
    private boolean a21;
    
    @Override
    public String toString(){
        String str = "";
        
        str += "viikko " + week + ": ";
        str += "tehtyjä tehtäviä yhteensä: ";
        String tehty = "";
        int n = 0;
        if(a1){
            tehty += "1 ";
            n++;
        }if(a2){
            tehty += "2 ";
            n++;
        }if(a3){
            tehty += "3 ";
            n++;
        }if(a4){
            tehty += "4 ";
            n++;
        }if(a5){
            tehty += "5 ";
            n++;
        }if(a6){
            tehty += "6 ";
            n++;
        }if(a7){
            tehty += "7 ";
            n++;
        }if(a8){
            tehty += "8 ";
            n++;
        }if(a9){
            tehty += "9 ";
            n++;
        }if(a10){
            tehty += "10 ";
            n++;
        }if(a11){
            tehty += "11 ";
            n++;
        }if(a12){
            tehty += "12 ";
            n++;
        }if(a13){
            tehty += "13 ";
            n++;
        }if(a14){
            tehty += "14 ";
            n++;
        }if(a15){
            tehty += "15 ";
            n++;
        }if(a16){
            tehty += "16 ";
            n++;
        }if(a17){
            tehty += "17 ";
            n++;
        }if(a18){
            tehty += "18 ";
            n++;
        }if(a19){
            tehty += "19 ";
            n++;
        }if(a20){
            tehty += "20 ";
            n++;
        }if(a21){
            tehty += "21 ";
            n++;
        }
        str += n;
        str += String.format(" (maksimi %s), aikaa kului ", "haloo");
        str += hours;
        str += " tuntia, tehdyt tehtävät: " + tehty;
        points = n;
        
        return str;
    }
}