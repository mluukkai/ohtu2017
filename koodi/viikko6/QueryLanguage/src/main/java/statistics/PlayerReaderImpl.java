package statistics;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerReaderImpl implements PlayerReader {

    private Scanner scanner;

    public PlayerReaderImpl(String pageUrl) {
        try {
            URL url = new URL(pageUrl);
            scanner = new Scanner(url.openStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<Player>();

        while (scanner.hasNextLine()) {
            String[] parts =  scanner.nextLine().split(";");            
            
            if (parts.length > 3) {
                players.add(new Player(parts[0].trim(), parts[1], extractInt(parts[3]), extractInt(parts[4])));
            }
        }

        return players;
    }

    private int extractInt(String str) {
        return Integer.parseInt(str.trim());
    }
}
