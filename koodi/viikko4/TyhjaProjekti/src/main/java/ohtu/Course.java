package ohtu;

/**
 * Created by migho on 9.4.2017.
 */
public class Course {
    private String name;
    private String term;
    private int week1;
    private int week2;
    private int week3;
    private int week4;
    private int week5;
    private int week6;
    private int n=0;

    public String getName() {
        return name;
    }

    public String getTerm() {
        return term;
    }

    public int getNext() {
        n++;
        if(n==1) return week1;
        if(n==2) return week2;
        if(n==3) return week3;
        if(n==4) return week4;
        if(n==5) return week5;
        if(n==6) return week6;
        return 0;
    }

}
