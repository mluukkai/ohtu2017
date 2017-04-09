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
 * @author imatias
 */
public class StatisticsTest {

    ReaderIO readerStub = new ReaderIO() {

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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void toimiikoSearchJosLoytyy() {
        
        assertEquals(new Player("Yzerman", "DET", 42, 56).getName(), stats.search("Yzerman").getName());
        
    }
    
    @Test
    public void toimiikoSearchJosEiLoydy() {
        
        assertEquals(null, stats.search("Kurriasdasdasd"));
    }
    
    @Test
    public void toimiikoTopScores() {
        assertEquals(new Player("Gretzky", "EDM", 35, 89).getName(), stats.topScorers(1).get(0).getName());
    }
    
    @Test
    public void toimiikoTeam() {
        assertEquals(new Player("Lemieux", "PIT", 45, 54).getName(), stats.team("PIT").get(0).getName());
    }
    
}
