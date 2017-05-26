package statistics.matcher;

import statistics.Player;

public class Not implements Matcher {
    private final Matcher inner;

    public Not(Matcher inner) {
        this.inner = inner;
    }

    @Override
    public boolean matches(Player p) {
        return !inner.matches(p);
    }
}
