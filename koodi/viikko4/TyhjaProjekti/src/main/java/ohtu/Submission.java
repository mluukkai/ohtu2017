package ohtu;

public class Submission {
    private String student_number;
    private int week;
    private int time;
    private int points;
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

    private int maxPoints;
    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    @Override
    public String toString(){
        String donez = "";
        int n = 0;
        if(a1){
            donez += "1 ";
            n++;
        }if(a2){
            donez += "2 ";
            n++;
        }if(a3){
            donez += "3 ";
            n++;
        }if(a4){
            donez += "4 ";
            n++;
        }if(a5){
            donez += "5 ";
            n++;
        }if(a6){
            donez += "6 ";
            n++;
        }if(a7){
            donez += "7 ";
            n++;
        }if(a8){
            donez += "8 ";
            n++;
        }if(a9){
            donez += "9 ";
            n++;
        }if(a10){
            donez += "10 ";
            n++;
        }if(a11){
            donez += "11 ";
            n++;
        }if(a12){
            donez += "12 ";
            n++;
        }if(a13){
            donez += "13 ";
            n++;
        }if(a14){
            donez += "14 ";
            n++;
        }if(a15){
            donez += "15 ";
            n++;
        }if(a16){
            donez += "16 ";
            n++;
        }if(a17){
            donez += "17 ";
            n++;
        }if(a18){
            donez += "18 ";
            n++;
        }if(a19){
            donez += "19 ";
            n++;
        }if(a20){
            donez += "20 ";
            n++;
        }if(a21){
            donez += "21 ";
            n++;
        }
        points = n;

        return "viikko " + week + ": tehtyjä tehtäviä yhteensä: " + n + " (maksimi " + maxPoints + "), aikaa kului " + time +
                " tuntia, tehdyt tehtävät: " + donez;
    }

    public String getStudent_number() {
        return student_number;
    }

    public int getWeek() {
        return week;
    }

    public int getTime() {
        return time;
    }

    public int getPoints() {
        return points;
    }
}