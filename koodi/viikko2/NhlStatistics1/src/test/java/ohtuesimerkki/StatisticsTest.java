package ohtuesimerkki;

import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by topi on 20.3.2017.
 */
public class StatisticsTest {
    Reader readerStub = new Reader() {

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

    @Before
    public void setUp() throws Exception {
        stats = new Statistics(readerStub);

    }

    @Test
    public void search() throws Exception {
        Player p;
        p = stats.search("Semenko");
        assertEquals(p.getName(), "Semenko");
    }
    @Test
    public void playerNotFound() {
        assertEquals(null, stats.search("Kalle"));
    }

    @Test
    public void team() throws Exception {
        List<Player> playersTest;
        playersTest = stats.team("EDM");

        assertEquals(3, playersTest.size());
    }

    @Test
    public void topScorers() throws Exception {
        List<Player> playersTest;
        playersTest = stats.topScorers(1);
        assertEquals(45, playersTest.get(1).getGoals());
    }


}