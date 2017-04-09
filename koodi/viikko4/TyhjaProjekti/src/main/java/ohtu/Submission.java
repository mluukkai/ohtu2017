package ohtu;

public class Submission {
    private String student_number;
    private Integer hours;
    private Integer week;
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
    
    public Integer howManyDone() {
        int done = 0;
        if (a1) done++;
        if (a2) done++;
        if (a3) done++;
        if (a4) done++;
        if (a5) done++;
        if (a6) done++;
        if (a7) done++;
        if (a8) done++;
        if (a9) done++;
        if (a10) done++;
        if (a11) done++;
        if (a12) done++;
        return done;
    }    
    
     public String whichDone() {
        String done = "";
        if (a1) done += "1 ";
        if (a2) done += "2 ";
        if (a3) done+= "3 ";
        if (a4) done+= "4 ";
        if (a5) done+= "5 ";
        if (a6) done+= "6 ";
        if (a7) done+= "7 ";
        if (a8) done+= "8 ";
        if (a9) done+= "9 ";
        if (a10) done+= "10 ";
        if (a11) done+= "11 ";
        if (a12) done+= "12 ";
        return done;
     }
    
    public String getStudent_number() {
        return student_number;
    }

    @Override
    public String toString() {
        return getStudent_number();
    }

    public Integer getHours() {
        return hours;
    }
    
    public Integer getWeek() {
        return week;
    }

    /**
     * @return the a1
     */
    public Boolean getA1() {
        return a1;
    }

    /**
     * @return the a2
     */
    public Boolean getA2() {
        return a2;
    }

    /**
     * @return the a3
     */
    public Boolean getA3() {
        return a3;
    }

    /**
     * @return the a4
     */
    public Boolean getA4() {
        return a4;
    }

    /**
     * @return the a5
     */
    public Boolean getA5() {
        return a5;
    }

    /**
     * @return the a6
     */
    public Boolean getA6() {
        return a6;
    }

    /**
     * @return the a7
     */
    public Boolean getA7() {
        return a7;
    }

    /**
     * @return the a8
     */
    public Boolean getA8() {
        return a8;
    }

    /**
     * @return the a9
     */
    public Boolean getA9() {
        return a9;
    }

    /**
     * @return the a10
     */
    public Boolean getA10() {
        return a10;
    }

    /**
     * @return the a11
     */
    public Boolean getA11() {
        return a11;
    }

    /**
     * @return the a12
     */
    public Boolean getA12() {
        return a12;
    }
    
}