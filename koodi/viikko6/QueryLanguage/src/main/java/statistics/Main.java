package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));
          
        Matcher m = new And( new HasAtLeast(10, "goals"), 
                             new Or(
                                new HasFewerThan(10, "assists"),
                                new PlaysIn("PHI")
                             )
        );
        
        QueryBuilder query = new QueryBuilder();
        
        Matcher oneOf = query.oneOf(
                query.hasFewerThan(10, "assists").build(), 
                query.playsIn("PHI").build())
                .build();
        Matcher allOf = query.allOf(query.hasAtLeast(10, "goals").build(), oneOf).build();
        
        
        for (Player player : stats.matches(allOf)) {
            System.out.println( player );
        }
    }
}
