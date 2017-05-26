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
 * @author ekalaja
 */
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
    public void testSearchWithPlayer() {
        Player semenko = stats.search("Semenko");
        assertEquals("EDM", semenko.getTeam());
    }
    
    @Test
    public void testSearchWithJack() {
        Player semenko = stats.search("Jack");
        assertEquals(null, semenko);
    }
    @Test
    public void testTeam() {
        List<Player> edm = stats.team("EDM");
        assertEquals(3, edm.size());
    }
    
    @Test
    public void testTopTwo() {
        List<Player> topTwo = stats.topScorers(2);
        assertEquals("Gretzky", topTwo.get(0).getName());
        assertEquals("Lemieux", topTwo.get(1).getName());
        
    }
    
}

//public class StatisticsTest {
//
//    public StatisticsTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of search method, of class Statistics.
//     */
//    @Test
//    public void testSearch() {
//        System.out.println("search");
//        String name = "";
//        Statistics instance = null;
//        Player expResult = null;
//        Player result = instance.search(name);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of team method, of class Statistics.
//     */
//    @Test
//    public void testTeam() {
//        System.out.println("team");
//        String teamName = "";
//        Statistics instance = null;
//        List<Player> expResult = null;
//        List<Player> result = instance.team(teamName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of topScorers method, of class Statistics.
//     */
//    @Test
//    public void testTopScorers() {
//        System.out.println("topScorers");
//        int howMany = 0;
//        Statistics instance = null;
//        List<Player> expResult = null;
//        List<Player> result = instance.topScorers(howMany);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    public class StaticsticsTest {
//
//        Reader readerStub = new Reader() {
//
//            public List<Player> getPlayers() {
//                ArrayList<Player> players = new ArrayList<Player>();
//
//                players.add(new Player("Semenko", "EDM", 4, 12));
//                players.add(new Player("Lemieux", "PIT", 45, 54));
//                players.add(new Player("Kurri", "EDM", 37, 53));
//                players.add(new Player("Yzerman", "DET", 42, 56));
//                players.add(new Player("Gretzky", "EDM", 35, 89));
//
//                return players;
//            }
//        };
//
//        Statistics stats;
//
//        @Before
//        public void setUp() {
//            // luodaan Staatistics-olio joka käyttää "stubia"
//            stats = new Statistics(readerStub);
//        }
//    }
//
//}
