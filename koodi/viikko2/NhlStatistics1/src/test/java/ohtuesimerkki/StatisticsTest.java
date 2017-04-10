package ohtuesimerkki;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StatisticsTest {
    Reader mockReader = () -> {
        ArrayList<Player> players = new ArrayList<>();

        players.add(new Player("Semenko", "EDM", 4, 12));
        players.add(new Player("Lemieux", "PIT", 45, 54));
        players.add(new Player("Kurri",   "EDM", 37, 53));
        players.add(new Player("Yzerman", "DET", 42, 56));
        players.add(new Player("Gretzky", "EDM", 35, 89));

        return players;
    };

    Statistics stats;

    @Before
    public void setUp() {
        stats = new Statistics(mockReader);
    }

    @Test
    public void searchWhenExists() {
        Player player = stats.search("Semenko");
        assertNotNull(player);
        assertEquals("EDM", player.getTeam());
    }

    @Test
    public void searchWhenNoResults() {
        Player player = stats.search("Haloo?");
        assertNull(player);
    }

    @Test
    public void teamWhenExists() {
        List<Player> edmPlayers = stats.team("EDM");
        assertEquals(3, edmPlayers.size());
        assertTrue(edmPlayers.stream().filter(p -> p.getName().equals("Gretzky")).findAny().isPresent());
    }

    @Test
    public void teamWhenNoResults() {
        List<Player> players = stats.team("No Haloo");
        assertEquals(0, players.size());
    }

    @Test
    public void top3Scorers() {
        List<Player> topPlayers = stats.topScorers(3);
        assertEquals("Gretzky", topPlayers.get(0).getName());
        assertEquals("Lemieux", topPlayers.get(1).getName());
        assertEquals("Yzerman", topPlayers.get(2).getName());
    }

    @Test
    public void top0Scorers() {
        List<Player> topPlayers = stats.topScorers(0);
        assertEquals(0, topPlayers.size());
    }
}