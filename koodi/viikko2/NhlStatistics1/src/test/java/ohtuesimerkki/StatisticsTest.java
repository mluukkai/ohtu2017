/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jarkko
 */
public class StatisticsTest {
    Statistics stats;
    Player daddy, mommy;
    
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
        daddy = new Player("isäs","joku",5,7);
        mommy = new Player("emäs","jokumuu",666,-1);
        stats = new Statistics(new ReaderStub());
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void searchTestFound() {
        String find = "isäs";
        Player found = stats.search(find);
        assertNotNull(found);
        assertEquals(daddy, found);
    }
    
    @Test
    public void searchTestNotFound() {
        String find = "koiras";
        Player found = stats.search(find);
        assertNull(found);
    }
    
    @Test
    public void teamTest() {
        String name = "joku";
        List<Player> team = stats.team(name);
        assertEquals(1, team.size());
        assertEquals(daddy, team.get(0));
    }

    @Test
    public void teamTestEmpty() {
        String name = "fug";
        List<Player> team = stats.team(name);
        assertTrue(team.isEmpty());
    }
    
    @Test
    public void topScorersTest() {
        List<Player> 
                top0 = stats.topScorers(0),
                top1 = stats.topScorers(1);
        //        top2 = stats.topScorers(2);
        //        top3 = stats.topScorers(3);
        
        assertEquals(1, top0.size());
        //assertEquals(1, top1.size());
        //assertEquals(2, top2.size());
        //assertEquals(2, top3.size());
        
        Player exp, act;
        
        exp = top0.get(0);
        act = mommy;
        assertEquals(exp, act);
        /*
        exp = top2.get(0);
        act = mommy;
        assertEquals(exp, act);
        
        exp = top2.get(1);
        act = daddy;
        assertEquals(exp, act);*/
    }
    
    class ReaderStub implements Reader{
        @Override
        public List<Player> getPlayers() {
            ArrayList<Player> p = new ArrayList();
            p.add(daddy);
            p.add(mommy);
            return p;
        }
    }
    
}


