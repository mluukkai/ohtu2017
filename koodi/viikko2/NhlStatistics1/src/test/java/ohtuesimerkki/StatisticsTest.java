
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


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
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void searchLoytaaOlemassaolevanPelaajan(){
        assertEquals("Kurri", stats.search("Kurri").getName());
    }
    
    @Test
    public void searchPalauttaaNullJosEiLoydy(){
        assertNull(stats.search("haribo nallekarkki"));
    }
    
    @Test
    public void teamPalauttaaOikeanKokoisenListan(){
        assertEquals(3, stats.team("EDM").size());
    }
    
    @Test
    public void teamPalauttaaNollaJosTiimiaEiOle(){
        assertEquals(0, stats.team("Haribo").size());
    }
    
    @Test
    public void teamPalauttaaOikeatPelaajat(){
        List<Player> players = stats.team("EDM");
        List<String> names = players.stream().map(p -> p.getName()).filter(s -> s.equals("Semenko") || s.equals("Kurri") || s.equals("Gretzky")).sorted().collect(Collectors.toCollection(ArrayList::new));
        boolean ok = names.size() == 3;
        if(ok){
            ok = ok && names.get(0).equals("Gretzky");
            ok = ok && names.get(1).equals("Kurri");
            ok = ok && names.get(2).equals("Semenko");
        }
        assertTrue(ok);
    }
    
    @Test
    public void topScorersPalauttaaOikeanKokoisenListan(){
        List<Player> pelaajat = stats.topScorers(3);
        assertEquals(4, pelaajat.size());
    }
    
    @Test
    public void topScorersPalauttaaOikeatPelaajat(){
        List<Player> pelaajat = stats.topScorers(2);
        Player eka = pelaajat.get(0);
        Player toka = pelaajat.get(1);
        
        assertTrue(eka.getName().equals("Gretzky") && toka.getName().equals("Lemieux"));
        
    }
    
    
}
