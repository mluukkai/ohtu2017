package statistics.matcher;

import java.lang.reflect.Method;
import statistics.Player;

public class QueryBuilder {

    private Matcher matcher;

    public QueryBuilder() {

    }

    public QueryBuilder playsIn(String teamName) {
        if (this.matcher == null) {
            this.matcher = new PlaysIn(teamName);
        } else {
            this.matcher = new And(this.matcher, new PlaysIn(teamName));
        }
        return this;
    }

    public QueryBuilder hasAtLeast(int number, String category) {
        if (this.matcher == null) {
            this.matcher = new HasAtLeast(number, category);
        } else {
            this.matcher = new And(this.matcher, new HasAtLeast(number, category));
        }
        return this;
    }

    public QueryBuilder hasFewerThan(int number, String category) {
        if (this.matcher == null) {
            this.matcher = new HasFewerThan(number, category);
        } else {
            this.matcher = new And(this.matcher, new HasFewerThan(number, category));
        }
        return this;
    }

    public Matcher build() {
        Matcher tempMatch;
        tempMatch = this.matcher;
        this.matcher = null;
        return tempMatch;
    }

    public QueryBuilder oneOf(Matcher m1, Matcher m2) {
        this.matcher = new Or(m1, m2);
        return this;
    }

}
