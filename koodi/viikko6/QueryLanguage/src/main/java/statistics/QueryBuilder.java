package statistics;

import statistics.matcher.*;

public class QueryBuilder {
    private final Matcher matcher;

    public QueryBuilder() {
        matcher = p -> true;
    }

    private QueryBuilder(Matcher matcher) {
        this.matcher = matcher;
    }

    public QueryBuilder playsIn(String team) {
        return new QueryBuilder(new And(matcher, p -> p.getTeam().equals(team)));
    }

    public QueryBuilder hasAtLeast(int value, String category) {
        return new QueryBuilder(new And(matcher, new HasAtLeast(value, category)));
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        return new QueryBuilder(new And(matcher, new HasFewerThan(value, category)));
    }

    public QueryBuilder oneOf(Matcher ...matchers) {
        return new QueryBuilder(new And(matcher, new Or(matchers)));
    }

    public Matcher build() {
        return matcher;
    }
}
