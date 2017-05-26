package statistics.matcher;

import statistics.Player;

public class Not implements Matcher {

    private Matcher matcher;

    public Not(Matcher matcher) {
        this.matcher = matcher;
    }

    @Override
    public boolean matches(Player p) {

        if (matcher.matches(p)) {
            return false;
        }


        return true;
    }
}
