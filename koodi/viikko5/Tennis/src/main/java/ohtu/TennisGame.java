package ohtu;

import java.util.HashMap;

public class TennisGame {

    private Player player1;
    private Player player2;
    private HashMap<Integer, String> points;

    public TennisGame(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
        this.points = new HashMap();
        this.constructPoints();
    }

    final void constructPoints() {
        this.points.put(0, "Love");
        this.points.put(1, "Fifteen");
        this.points.put(2, "Thirty");
        this.points.put(3, "Forty");
    }

    public void wonPoint(String playerName) {
        Player player = getPlayer(playerName);
        player.setScore(player.getScore() + 1);
    }

    public String getScore() {
        String score = "";
        if (gameIsTie()) {
            score = tieSituation();
        } else if (player1.getScore() >= 4 || player2.getScore() >= 4) {
            score = checkIfPlayerIsWinning();
        } else {
            score = returnGameSituation();
        }
        return score;
    }

    private boolean gameIsTie() {
        return player1.getScore() == player2.getScore();
    }

    public String tieSituation() {
        String ret = this.points.get(player1.getScore());
        if (ret == null) {
            ret = "Deuce";
        } else {
            ret += "-All";
        }
        return ret;
    }

    private String checkIfPlayerIsWinning() {
        int pointDifference = this.player1.getScore() - this.player2.getScore();
        String ret = "";
        if (Math.abs(pointDifference) == 1) {
            ret = "Advantage ";
            if (pointDifference > 0) {
                ret += this.player1.getName();
            } else {
                ret += this.player2.getName();
            }
        } else {
            ret = "Win for ";
            if (pointDifference > 1) {
                ret += this.player1.getName();
            } else {
                ret += this.player2.getName();
            }

        }
        return ret;
    }

    private Player getPlayer(String playerName) {
        if (playerName.equals(player1.getName())) {
            return player1;
        } else {
            return player2;
        }
    }

    private String returnGameSituation() {
        return "" + this.points.get(this.player1.getScore()) + "-" + this.points.get(this.player2.getScore());
    }

    public static void main(String[] args) {
        TennisGame game = new TennisGame("player1", "player2");

        System.out.println(game.getScore());

        game.wonPoint("player1");
        System.out.println(game.getScore());

        game.wonPoint("player1");
        System.out.println(game.getScore());

        game.wonPoint("player2");
        System.out.println(game.getScore());

        game.wonPoint("player1");
        System.out.println(game.getScore());

        game.wonPoint("player1");
        System.out.println(game.getScore());
    }

}
