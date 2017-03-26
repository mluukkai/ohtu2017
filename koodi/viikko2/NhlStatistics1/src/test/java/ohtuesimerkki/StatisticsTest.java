package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

//@author Leevi
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


    public StatisticsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }
    
    @After
    public void tearDown() {
    }

  /**
   * Test of search method, of class Statistics.
   */
  @Test
  public void testSearch1() {

    String name = "Semenko";
    Player result = stats.search(name);
    assertEquals(result.getName(), name);
  }
  
  @Test
  public void testSearch2() {

    String name = "Kalle";
    Player result = stats.search(name);
    assertNull(result);
  }

  /**
   * Test of team method, of class Statistics.
   */
  @Test
  public void testTeam() {

      String name = "PIT";
      List<Player> team = stats.team("PIT");
      assertEquals(team.get(0).getName(), "Lemieux");
  }

  /**
   * Test of topScorers method, of class Statistics.
   */
  @Test
  public void testTopScorers() {

      Integer howMany = 2;
      List<Player> team = stats.topScorers(howMany);
      assertEquals(team.get(0).getName(), "Gretzky");
  }

}