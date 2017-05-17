/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

import java.util.ArrayList;
import statistics.Player;

/**
 *
 * @author jarkko
 */
public class QueryBuilder {
    private ArrayList<Matcher> ma;
    public QueryBuilder(){
        ma = new ArrayList<>();
    }
    
    public QueryBuilder and(Matcher... matchers){
        ma.add(new And(matchers));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category){
        ma.add(new HasAtLeast(value, category));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int num, String field){
        ma.add(new HasFewerThan(num, field));
        return this;
    }
    
    public QueryBuilder not(Matcher m){
        ma.add(new Not(m));
        return this;
    }
    
    public QueryBuilder or(Matcher... matchers){
        ma.add(new And(matchers));
        return this;
    }
    
    public QueryBuilder playsIn(String team){
        ma.add(new PlaysIn(team));
        return this;
    }
    
    public Matcher build(){
        return new And((Matcher[])ma.toArray());
    }
}
