package statistics.matcher;

import statistics.Player;

public class All implements Matcher {

    public All() {
    }

    @Override
    public boolean matches(Player p) {
        return true;
    }

}
