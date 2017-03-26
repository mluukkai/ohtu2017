/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author saskeli
 */
public class StatisticsTest {
    Statistics stats;
    Reader readerStub = new Reader() {
        @Override
        public List<Player> getPlayers() {
            List<Player> players = new ArrayList<>();
            players.add(new Player("Mr. Fingers", "House", 3, 22));
            players.add(new Player("Above and Beyond", "Trance", 10, 32));
            players.add(new Player("Infected Mushroom", "Goa Trance", 11, 38));
            players.add(new Player("Super8 & Tab", "Trance", 4, 11));
            players.add(new Player("Pendulum", "Drum and Bass", 4, 23));
            return players;
        }
    };
    
    public StatisticsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void searchTest() {
        assertEquals("Trance", stats.search("Above and Beyond").getTeam());
        assertEquals(null, stats.search("CMX"));
    }
    
    @Test
    public void teamSearchTest() {
        List<Player> tp = stats.team("Trance");
        assertEquals(2, tp.size());
    }
    
    @Test
    public void scoreSearchTest() {
        List<Player> sp = stats.topScorers(1);
        assertEquals(1, sp.size());
        assertEquals("Infected Mushroom", sp.get(0).getName());
    }
}
