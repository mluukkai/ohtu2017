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
 * @author tiera
 */
public class StatisticsTest {

    Reader readerStub = new Reader() {

        @Override
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

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

    /**
     * Test of search method, of class Statistics.
     */
    @Test
    public void testSearch() {
        Player k = new Player("Semenko", "EDM", 4, 12);
        Player t = stats.search("Semenko");
        assertEquals(k.toString(), t.toString());
        t = stats.search("adsas");
        assertEquals(null, t);
    }

    /**
     * Test of team method, of class Statistics.
     */
    @Test
    public void testTeam() {
        List<Player> d = new ArrayList<>();
        d.add(new Player("Semenko", "EDM", 4, 12));
        d.add(new Player("Kurri", "EDM", 37, 53));
        d.add(new Player("Gretzky", "EDM", 35, 89));
        List<Player> s = stats.team("EDM");
        assertEquals(d.get(0).toString(), s.get(0).toString());
        assertEquals(d.get(1).toString(), s.get(1).toString());
        assertEquals(d.get(2).toString(), s.get(2).toString());
    }

    /**
     * Test of topScorers method, of class Statistics.
     */
    @Test
    public void testTopScorers() {
        List<Player> k = stats.topScorers(1);
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Semenko", "EDM", 4, 12));
        players.add(new Player("Lemieux", "PIT", 45, 54));
        players.add(new Player("Kurri", "EDM", 37, 53));
        players.add(new Player("Yzerman", "DET", 42, 56));
        players.add(new Player("Gretzky", "EDM", 35, 89));
        
        assertEquals(players.get(4).toString(), k.get(0).toString());
        //assertEquals(players.get(1).toString(), k.get(1).toString());
        //assertEquals(players.get(3).toString(), k.get(2).toString());
        //assertEquals(players.get(2).toString(), k.get(3).toString());
        //assertEquals(players.get(0).toString(), k.get(4).toString());
         
    }

}
