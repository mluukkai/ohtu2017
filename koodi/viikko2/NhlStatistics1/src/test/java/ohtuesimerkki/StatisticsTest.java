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
 * @author villepaa
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
    public void setUp() {
        stats = new Statistics(readerStub);
    }
  

    @Test
    public void searchPalauttaaNullJosEiLöydy() {
        System.out.println("search");
        String name = "Barkov";
        Player expResult = null;
        Player result = stats.search(name);
        System.out.println("exp: " + expResult + ", res: " + result);
        assertEquals(expResult, result);
        
    }
    
    
    @Test
    public void searchPalauttaaLöydetyn() {
        System.out.println("search");
        String name = "Kurri";
        Player result = stats.search(name);
        System.out.println("exp: " + name + ", res: " + result.getName());
        assertEquals(name, result.getName());
    }

    @Test
    public void testTeam() {
        System.out.println("team");
        String teamName = "EDM";
        int expResult = 3;
        List<Player> result = stats.team(teamName);
        System.out.println("exp. pituus: " + expResult + ", pituus: " + result.size());
        assertEquals(expResult, result.size());
        
    }

    @Test
    public void oikeaMääräTopScorereita() {
        System.out.println("topScorers");
        int howMany = 3;
              
        List<Player> result = stats.topScorers(howMany);
        System.out.println("exp. pituus: " + howMany + ", pituus: " + result.size());
        assertEquals(3, result.size());
        
    }
    
    @Test
    public void oikeatHenkilötTopScorereina(){
        System.out.println("topScorers");
        int howMany = 3;
              
        List<Player> result = stats.topScorers(howMany);
        
        System.out.println("odotettu kolmas: Yzerman" + ", kolmas: " + result.get(2).getName());
        assertEquals("Yzerman", result.get(2).getName());
    }
    
}
