
package statistics;

import statistics.matcher.*;


public class QueryBuilder {
    private Matcher matcher;

    public QueryBuilder() {
        this.matcher = new And(); 
    }
    
    public QueryBuilder hasAtLeast(int value, String category){
        this.matcher = new And(this.matcher, new HasAtLeast(value, category));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String category){
        this.matcher = new And(this.matcher, new HasFewerThan(value, category));
        return this;
    }
    
    public QueryBuilder playsIn(String team){
        this.matcher = new And(this.matcher, new PlaysIn(team));
        return this;
    }
    
    public QueryBuilder oneOf(Matcher... matchers){
        this.matcher = new Or(matchers);
        return this;
    }
    
    
    public QueryBuilder allOf(Matcher... matchers){
        this.matcher = new And(matchers);
        return this;
    }
    
    public Matcher build(){
        Matcher palauta = this.matcher;
        this.matcher = new And();
        return palauta;
    }
    
    
}
