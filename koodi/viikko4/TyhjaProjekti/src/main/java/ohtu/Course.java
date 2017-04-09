package ohtu;

import java.util.ArrayList;

public class Course {
    private String name;
    private String term;
    private Integer week1;
    private Integer week2;
    private Integer week3;
    private Integer week4;
    private Integer week5;
    private Integer week6;

    public int[] getWeeks() {
        int[] weeks = new int[7];
        weeks[1] = week1;
        weeks[2] = week2;
        weeks[3] = week3;
        weeks[4] = week4;
        weeks[5] = week5;
        weeks[6] = week6;
        return weeks;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the term
     */
    public String getTerm() {
        return term;
    }

    /**
     * @return the week1
     */
    public Integer getWeek1() {
        return week1;
    }

    /**
     * @return the week2
     */
    public Integer getWeek2() {
        return week2;
    }

    /**
     * @return the week3
     */
    public Integer getWeek3() {
        return week3;
    }

    /**
     * @return the week4
     */
    public Integer getWeek4() {
        return week4;
    }

    /**
     * @return the week5
     */
    public Integer getWeek5() {
        return week5;
    }

    /**
     * @return the week6
     */
    public Integer getWeek6() {
        return week6;
    }

    
    
}