
package ohtu;


public class Kurssi {
    
    private String name, week1, week2, week3, week4, week5, week6, term;

    public String getTerm() {
        return term;
    }

    public String getName() {
        return name;
    }

    public String getWeek(int i) {
        if (i==1){return week1;
        } else if (i==2){return week2;
        } else if (i==3){return week3;
        } else if (i==4){return week4;
        } else if (i==5){return week5;
        } else return week6;
    }

    
    
}
