/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.util.ArrayList;

/**
 *
 * @author jjniitty
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
    private ArrayList<Integer> maxlist;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the term
     */
    public String getTerm() {
        return term;
    }

    /**
     * @param term the term to set
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * @return the week1
     */
    public int getWeek1() {
        return week1;
    }

    /**
     * @param week1 the week1 to set
     */
    public void setWeek1(int week1) {
        this.week1 = week1;
    }

    /**
     * @return the week2
     */
    public int getWeek2() {
        return week2;
    }

    /**
     * @param week2 the week2 to set
     */
    public void setWeek2(int week2) {
        this.week2 = week2;
    }

    /**
     * @return the week3
     */
    public int getWeek3() {
        return week3;
    }

    /**
     * @param week3 the week3 to set
     */
    public void setWeek3(int week3) {
        this.week3 = week3;
    }

    /**
     * @return the week4
     */
    public int getWeek4() {
        return week4;
    }

    /**
     * @param week4 the week4 to set
     */
    public void setWeek4(int week4) {
        this.week4 = week4;
    }

    /**
     * @return the week5
     */
    public int getWeek5() {
        return week5;
    }

    /**
     * @param week5 the week5 to set
     */
    public void setWeek5(int week5) {
        this.week5 = week5;
    }

    /**
     * @return the week6
     */
    public int getWeek6() {
        return week6;
    }

    /**
     * @param week6 the week6 to set
     */
    public void setWeek6(int week6) {
        this.week6 = week6;
    }
    
    public void createMaxlist(){
        maxlist = new ArrayList<Integer>();
        maxlist.add(week1);
        maxlist.add(week2);
        maxlist.add(week3);
        maxlist.add(week4);
        maxlist.add(week5);
        maxlist.add(getWeek6());
        
    }

    /**
     * @return the maxlist
     */
    public ArrayList<Integer> getMaxlist() {
        return maxlist;
    }
    
}
