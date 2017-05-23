package ohtu;

import java.util.Arrays;
import java.util.List;

public class TennisGame {
    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1") {
            player1Score += 1;
        } else {
            player2Score += 1;
        }
    }

    public String getScore() {
        String score = "";
        if (isEven()) {
            score = getEvenScoreString(player1Score);
        } else if (isAdvantage()) {
            int minusResult = player1Score - player2Score;
            score = getAdvantageString(minusResult);
        } else {
            score += getPointString(player1Score, player2Score);
        }
        return score;
    }

    private String getEvenScoreString(int score) {
        if (score > 3) {
            return "Deuce";
        }
        List<String> scoreStrings = Arrays.asList("Love-All", "Fifteen-All",
                                                  "Thirty-All", "Forty-All");
        return scoreStrings.get(score);
    }
    
    private boolean isAdvantage() {
        return player1Score >= 4 || player2Score >= 4;
    }

    private String getAdvantageString(int minusResult) {
        if (minusResult == 1) {
            return "Advantage player1";
        } else if (minusResult == -1) {
            return "Advantage player2";
        } else if (minusResult >= 2) {
            return "Win for player1";
        } else {
            return "Win for player2";
        }
    }

    private String getPointString(int p1, int p2) {
        List<String> pointStrings = Arrays.asList("Love", "Fifteen", "Thirty", "Forty");
        return pointStrings.get(p1) + "-" + pointStrings.get(p2);
    }
    
    private boolean isEven(){
        return player1Score == player2Score;
    }

}
