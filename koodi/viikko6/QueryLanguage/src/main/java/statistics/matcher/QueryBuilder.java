package statistics.matcher;

import java.util.ArrayList;

/**
 * Created by migho on 2.5.2017.
 */
public class QueryBuilder {
    private ArrayList<Matcher> matchers = new ArrayList<>();

    public QueryBuilder hasAtLeast(int value, String category) {
        matchers.add(new HasAtLeast(value, category));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        matchers.add(new HasFewerThan(value, category));
        return this;
    }

    public QueryBuilder and(Matcher... matchers) {
        this.matchers.add(new And(matchers));
        return this;
    }

    public QueryBuilder not(Matcher m) {
        this.matchers.add(new Not(m));
        return this;
    }

    public QueryBuilder or(Matcher... matchers) {
        this.matchers.add(new Or(matchers));
        return this;
    }

    public QueryBuilder playsIn(String team) {
        matchers.add(new PlaysIn(team));
        return this;
    }

    public Matcher build() {
        Matcher matcher = new And(matchers.toArray(new Matcher[matchers.size()]));
        matchers.clear();
        return matcher;
    }

}