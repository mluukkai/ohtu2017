/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jjniitty
 */
public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        // luodaan Staatistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
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

    @Test
    public void searchToimii() {
        Player x = new Player("Kurri", "EDM", 37, 53);
        assertEquals(x.getName(), stats.search("Kurri").getName());
    }

    @Test
    public void searchPalauttaaNullinJosEiLoyda() {
        assertEquals(null, stats.search("Jadatijaa"));
    }
    
    @Test
    public void teamSearchToimii() {
        assertEquals(stats.team("PIT").get(0).getName(), "Lemieux");
    }
    
    @Test
    public void teamSearchPalauttaaTyhjanListan() {
        assertTrue(stats.team("HKL").isEmpty());
    }

    @Test
    public void topScorersToimii() {
        assertEquals(stats.topScorers(1).get(1).getName(), "Lemieux");
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
