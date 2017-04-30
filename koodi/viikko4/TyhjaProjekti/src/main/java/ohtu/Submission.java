package ohtu;


public class Submission {
    
    private int week;
    private String student_number;
    private int hours;
   
    
    private boolean a1;private boolean a2;private boolean a3;private boolean a4;
    private boolean a5;private boolean a6;private boolean a7;private boolean a8;
    private boolean a9;private boolean a10;private boolean a11;private boolean a12;
    private boolean a13;private boolean a14;private boolean a15;private boolean a16;
    private boolean a17;private boolean a18;private boolean a19;private boolean a20;
    private boolean a21;
    
    

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number,int week, int hours,
            boolean a1, boolean a2,boolean a3, boolean a4,
            boolean a5,boolean a6,boolean a7,boolean a8,boolean a9,boolean a10,boolean a11,boolean a12,boolean a13,
            boolean a14,boolean a15,boolean a16,boolean a17,boolean a18,boolean a19,boolean a20,boolean a21) {
        
        
        this.student_number = student_number;
        this.week = week;
        this.hours = hours;
       
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.a5 = a5;
        this.a6 = a6;
        this.a7 = a7;
        this.a8 = a8;
        this.a9 = a9;
        this.a10 = a10;
        this.a11 = a11;
        this.a12 = a12;
        this.a13 = a13;
        this.a14 = a14;
        this.a15 = a15;
        this.a16 = a16;
        this.a17 = a17;
        this.a18 = a18;
        this.a19 = a19;
        this.a20 = a20;
        this.a21 = a21;
                
    }

    public boolean isA1() {
        return a1;
    }

    public void setA1(boolean a1) {
        this.a1 = a1;
    }

    public boolean isA2() {
        return a2;
    }

    public void setA2(boolean a2) {
        this.a2 = a2;
    }

    public boolean isA3() {
        return a3;
    }

    public void setA3(boolean a3) {
        this.a3 = a3;
    }

    public boolean isA4() {
        return a4;
    }

    public void setA4(boolean a4) {
        this.a4 = a4;
    }

    public boolean isA5() {
        return a5;
    }

    public void setA5(boolean a5) {
        this.a5 = a5;
    }

    public boolean isA6() {
        return a6;
    }

    public void setA6(boolean a6) {
        this.a6 = a6;
    }

    public boolean isA7() {
        return a7;
    }

    public void setA7(boolean a7) {
        this.a7 = a7;
    }

    public boolean isA8() {
        return a8;
    }

    public void setA8(boolean a8) {
        this.a8 = a8;
    }

    public boolean isA9() {
        return a9;
    }

    public void setA9(boolean a9) {
        this.a9 = a9;
    }

    public boolean isA10() {
        return a10;
    }

    public void setA10(boolean a10) {
        this.a10 = a10;
    }

    public boolean isA11() {
        return a11;
    }

    public void setA11(boolean a11) {
        this.a11 = a11;
    }

    public boolean isA12() {
        return a12;
    }

    public void setA12(boolean a12) {
        this.a12 = a12;
    }

    public boolean isA13() {
        return a13;
    }

    public void setA13(boolean a13) {
        this.a13 = a13;
    }

    public boolean isA14() {
        return a14;
    }

    public void setA14(boolean a14) {
        this.a14 = a14;
    }

    public boolean isA15() {
        return a15;
    }

    public void setA15(boolean a15) {
        this.a15 = a15;
    }

    public boolean isA16() {
        return a16;
    }

    public void setA16(boolean a16) {
        this.a16 = a16;
    }

    public boolean isA17() {
        return a17;
    }

    public void setA17(boolean a17) {
        this.a17 = a17;
    }

    public boolean isA18() {
        return a18;
    }

    public void setA18(boolean a18) {
        this.a18 = a18;
    }

    public boolean isA19() {
        return a19;
    }

    public void setA19(boolean a19) {
        this.a19 = a19;
    }

    public boolean isA20() {
        return a20;
    }

    public void setA20(boolean a20) {
        this.a20 = a20;
    }

    public boolean isA21() {
        return a21;
    }

    public void setA21(boolean a21) {
        this.a21 = a21;
    }
    
    public String tehtavat(){
         String tehtavat = "";
        int maara = 0;
         if(a1){
            tehtavat =  tehtavat + " " +   "1";
            maara++;
        }
        if(a2){
            tehtavat =  tehtavat + " " + "2";
            maara++;
        }
        if(a3){
            tehtavat =  tehtavat + " " + "3";
            maara++;
        }
        if(a4){
            tehtavat =  tehtavat +" " +  "4";
            maara++;
        }
        if(a5){
            tehtavat =  tehtavat + " " + "5";
            maara++;
        }
        if(a6){
            tehtavat =  tehtavat +" " +  "6";
            maara++;
        }
        if(a7){
            tehtavat =  tehtavat +" " +  "7";
            maara++;
        }
        if(a8){
            tehtavat =  tehtavat + " " +  "8";
            maara++;
        }
        if(a9){
            tehtavat =  tehtavat + " " + "9";
            maara++;
        }
        if(a10){
            tehtavat =  tehtavat + " " + "10";
            maara++;
        }if(a11){
            tehtavat =  tehtavat +" " +  "11";
            maara++;
        }if(a12){
            tehtavat =  tehtavat +" " +  "12";
            maara++;
        }
        return ", aikaa kului " + hours + " tuntia. Tehdyt tehtävät:" + tehtavat;
    }
        
    @Override
    public String toString() {
        String tehtavat = "";
        int maara = 0;
        
        if(a1){
            tehtavat =  tehtavat + " " +   "1";
            maara++;
        }
        if(a2){
            tehtavat =  tehtavat + " " + "2";
            maara++;
        }
        if(a3){
            tehtavat =  tehtavat + " " + "3";
            maara++;
        }
        if(a4){
            tehtavat =  tehtavat +" " +  "4";
            maara++;
        }
        if(a5){
            tehtavat =  tehtavat + " " + "5";
            maara++;
        }
        if(a6){
            tehtavat =  tehtavat +" " +  "6";
            maara++;
        }
        if(a7){
            tehtavat =  tehtavat +" " +  "7";
            maara++;
        }
        if(a8){
            tehtavat =  tehtavat + " " +  "8";
            maara++;
        }
        if(a9){
            tehtavat =  tehtavat + " " + "9";
            maara++;
        }
        if(a10){
            tehtavat =  tehtavat + " " + "10";
            maara++;
        }if(a11){
            tehtavat =  tehtavat +" " +  "11";
            maara++;
        }if(a12){
            tehtavat =  tehtavat +" " +  "12";
            maara++;
        }
                
        return "viikko "+  week + ": Tehtyjä tehtäviä yhteensä: " + maara;
    }
    
}