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
 * @author luhtalam
 */
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
    public void testSearchLoytaaPelaajan() {
        Player p = stats.search("Kurri");
        assertEquals(p.getName(), "Kurri");
        assertEquals(p.getTeam(), "EDM");
        assertEquals(p.getGoals(), 37);
        assertEquals(p.getAssists(), 53);
    }
    
    @Test
    public void testSearchPalauttaaNullKunPelaajaaEiOle() {
        assertNull(stats.search("Aertgnsot"));
    }
    
    @Test
    public void testTeamPalauttaaOikeatPelaajat() {
        List<Player> l = stats.team("EDM");
        assertEquals(l.size(),3);
        if(l.size() == 3) {
            assertEquals(l.get(0).getName(),"Semenko");
            assertEquals(l.get(1).getName(),"Kurri");
            assertEquals(l.get(2).getName(),"Gretzky");
        }
    }
    
    @Test
    public void testTeamPalauttaaTyhjanListanOlemattomalleJoukkeelle() {
        List<Player> l = stats.team("AKSNDONAO");
        assertEquals(l.size(),0);
    }
    
    @Test
    public void testTopScoresPalauttaaTyhjanListanJosParametriNegatiivinen() {
        List<Player> l = stats.topScorers(-2);
        assertEquals(l.size(),0);
    }
    
    @Test
    public void testTopScoresPalauttaaTyhjanListanJosParametriNolla() {
        List<Player> l = stats.topScorers(0);
        assertEquals(l.size(),0);
    }
    
    @Test
    public void testTopScoresPalauttaaOikein() {
        List<Player> l = stats.topScorers(2);
        assertEquals(l.size(),2);
        if(l.size() == 2) {
            assertEquals(l.get(0).getName(), "Gretzky");
            assertEquals(l.get(1).getName(), "Lemieux");
        }
    }
}
