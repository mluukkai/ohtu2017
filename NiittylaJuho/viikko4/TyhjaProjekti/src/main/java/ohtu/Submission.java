package ohtu;

import java.util.ArrayList;

public class Submission {

    private String student_number;
    private int week;
    private int hours;
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
    private ArrayList<Integer> done;

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    @Override
    public String toString() {
        return student_number;
    }

    /**
     * @return the week
     */
    public int getWeek() {
        return week;
    }

    /**
     * @param week the week to set
     */
    public void setWeek(int week) {
        this.week = week;
    }

    /**
     * @return the hours
     */
    public int getHours() {
        return hours;
    }

    /**
     * @param hours the hours to set
     */
    public void setHours(int hours) {
        this.hours = hours;
    }

    /**
     * @param a1 the a1 to set
     */
    public void setA1(boolean a1) {
        this.a1 = a1;
    }

    /**
     * @param a2 the a2 to set
     */
    public void setA2(boolean a2) {
        this.a2 = a2;
    }

    /**
     * @param a3 the a3 to set
     */
    public void setA3(boolean a3) {
        this.a3 = a3;
    }

    /**
     * @param a4 the a4 to set
     */
    public void setA4(boolean a4) {
        this.a4 = a4;
    }

    /**
     * @param a5 the a5 to set
     */
    public void setA5(boolean a5) {
        this.a5 = a5;
    }

    /**
     * @param a6 the a6 to set
     */
    public void setA6(boolean a6) {
        this.a6 = a6;
    }

    /**
     * @param a7 the a7 to set
     */
    public void setA7(boolean a7) {
        this.a7 = a7;
    }

    /**
     * @param a8 the a8 to set
     */
    public void setA8(boolean a8) {
        this.a8 = a8;
    }

    /**
     * @param a9 the a9 to set
     */
    public void setA9(boolean a9) {
        this.a9 = a9;
    }

    /**
     * @param a10 the a10 to set
     */
    public void setA10(boolean a10) {
        this.a10 = a10;
    }

    /**
     * @param a11 the a11 to set
     */
    public void setA11(boolean a11) {
        this.a11 = a11;
    }

    /**
     * @param a12 the a12 to set
     */
    public void setA12(boolean a12) {
        this.a12 = a12;
    }

    public void createDone() {
        done = new ArrayList<Integer>();
        if (a1 == true) {
            done.add(1);
        }
        if (a2 == true) {
            done.add(2);
        }
        if (a3 == true) {
            done.add(3);
        }
        if (a4 == true) {
            done.add(4);
        }
        if (a5 == true) {
            done.add(5);
        }
        if (a6 == true) {
            done.add(6);
        }
        if (a7 == true) {
            done.add(7);
        }
        if (a8 == true) {
            done.add(8);
        }
        if (a9 == true) {
            done.add(9);
        }
        if (a10 == true) {
            done.add(10);
        }
        if (a11 == true) {
            done.add(11);
        }
        if (a12 == true) {
            done.add(12);
        }
    }

    /**
     * @return the done
     */
    public ArrayList<Integer> getDone() {
        return done;
    }

}
