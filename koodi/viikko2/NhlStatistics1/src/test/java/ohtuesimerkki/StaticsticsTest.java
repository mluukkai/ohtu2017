package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class StaticsticsTest {
 
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
    public void SearchTest(){
      assertNotNull(stats.search("Semenko"));
    }
    @Test
    public void SearchTest2(){
      assertNull(stats.search("Semenko2"));
    }
    @Test
    public void TeamTest(){
      assertNotNull(stats.team("EDM"));
    }
    @Test
    public void topScorers(){
      assertNotNull(stats.topScorers(1));
    }
}