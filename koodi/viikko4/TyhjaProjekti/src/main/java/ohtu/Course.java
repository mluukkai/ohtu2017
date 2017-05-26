
package ohtu;


public class Course {
    private String name;
    private String term;
    private int week1;
    private int week2;
    private int week3;
    private int week4;
    private int week5;
    private int week6;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    
    
    public int maxEx(int week){
        if(week == 1){
            return week1;
        }
        if(week == 2){
            return week2;
        }
        if(week == 3){
            return week3;
        }
        if(week == 4){
            return week4;
        }
        if(week == 5){
            return week5;
        }
        return week6;
        
    }
    
    public int getWeek1() {
        return week1;
    }

    public int getWeek2() {
        return week2;
    }

    public int getWeek3() {
        return week3;
    }

    public int getWeek4() {
        return week4;
    }

    public int getWeek5() {
        return week5;
    }

    public int getWeek6() {
        return week6;
    }


    public void setWeek1(int week1) {
        this.week1 = week1;
    }

    public void setWeek2(int week2) {
        this.week2 = week2;
    }

    public void setWeek3(int week3) {
        this.week3 = week3;
    }

    public void setWeek4(int week4) {
        this.week4 = week4;
    }

    public void setWeek5(int week5) {
        this.week5 = week5;
    }
    
    public void setWeek6(int week6) {
        this.week6 = week6;
    }

    
    
    
          
}
