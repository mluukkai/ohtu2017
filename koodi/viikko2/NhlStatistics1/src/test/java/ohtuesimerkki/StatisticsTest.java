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
 * @author juicyp
 */
public class StatisticsTest {
    
    Statistics st;
    List<Player> players;
    
    @Before
    public void setUp() {
        players = new ArrayList<Player>();
        players.add(new Player("Jussi Kinnunen", "Pussit", 0, 0));
        players.add(new Player("Jäbä Leissön", "Kaljat", 10, 5));
        st = new Statistics(new ReaderStub(players));
    }
    
    @Test
    public void hakuLoytaaJosSisaltyy() {
        String nimi = players.get(0).getName();
        assertEquals(players.get(0), st.search(nimi));
    }
    
    @Test
    public void hakuEiLoydaJosEiSisally() {
        String nimi = "ei oo";
        assertNull(st.search(nimi));
    }
    
    @Test
    public void joukkueListaanSisaltyy() {
        String joukkue = players.get(0).getTeam();
        assertEquals(players.get(0), st.team(joukkue).get(0));
    }
    
    @Test
    public void joukkueListaanEiSisally() {
        String joukkue = "ei oo";
        assertEquals(0, st.team(joukkue).size());
    }
    
    @Test
    public void parasPistemiesOnOikea() {
        assertEquals(players.get(1), st.topScorers(1).get(0));
    }
    
}

class ReaderStub implements Reader {
    
    private List<Player> players;
    
    public ReaderStub(List<Player> players) {
        this.players = players;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }
    
}