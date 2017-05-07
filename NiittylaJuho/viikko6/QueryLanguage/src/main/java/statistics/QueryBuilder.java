
package statistics;

import statistics.matcher.*;

public class QueryBuilder {

    private Matcher matcher;

    public QueryBuilder() {
        this.matcher = new All();
    }

    public QueryBuilder(QueryBuilder querybuilder, Matcher matcher) {
        this.matcher = matcher;
    }

    public Matcher build() {
        return this.matcher;
    }

    public QueryBuilder playsIn(String team) {
        return new QueryBuilder(this, new And(this.matcher, new PlaysIn(team)));
    }

    public QueryBuilder hasAtLeast(int value, String category) {
        return new QueryBuilder(this, new And(this.matcher, new HasAtLeast(value, category)));
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        return new QueryBuilder(this, new And(this.matcher, new HasFewerThan(value, category)));
    }

    public QueryBuilder oneOf(Matcher... matchers) {
        return new QueryBuilder(this, new And(this.matcher, new Or(matchers)));
    }

}