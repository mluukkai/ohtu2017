package ohtuesimerkki;


import java.util.ArrayList;
import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;
import ohtuesimerkki.Statistics;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Staatistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void topScorerWorks() {
        assertEquals(stats.topScorers(1).get(0).getName(), "Gretzky");
    }
    
    @Test
    public void playerSearchWorks() {
        assertEquals(stats.search("Kurri").getName(), "Kurri");
    }
    
    @Test
    public void playerSearchNotFoundReturnsNull() {
        assertEquals(stats.search("Peinola"), null);
    }
    
    @Test
    public void playersOfTeamReturnsPlayer() {
        assertEquals(stats.team("DET").get(0).getName(), "Yzerman");
    }
    
}