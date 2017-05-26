package statistics;

import statistics.matcher.*;

public class Main {

    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));

        QueryBuilder query = new QueryBuilder();

        System.out.println("QUERY 1:");

        Matcher m2 = query.playsIn("CHI")
                .hasAtLeast(10, "goals")
                .hasAtLeast(10, "assists").build();

        Matcher m3 = query.playsIn("TOR")
                .hasAtLeast(50, "points").build();

        Matcher m4 = query.oneOf(m2, m3).build();

        for (Player player : stats.matches(m4)) {
            System.out.println(player);
        }

        System.out.println("QUERY 2:");

        Matcher mTesti2 = query.playsIn("DET").hasFewerThan(1, "goals").build();

        for (Player player : stats.matches(mTesti2)) {
            System.out.println(player);
        }

        System.out.println("QUERY 3:");

        Matcher mTesti3 = query.playsIn("VAN").hasFewerThan(5, "goals").hasAtLeast(10, "assists").build();

        for (Player player : stats.matches(mTesti3)) {
            System.out.println(player);
        }

        System.out.println("QUERY 4:");

        Matcher m1 = query.playsIn("CHI")
                .hasAtLeast(20, "goals")
                .hasFewerThan(30, "assists").build();

        for (Player player : stats.matches(m1)) {
            System.out.println(player);
        }
    }
}
