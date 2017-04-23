package ohtu;

public class TennisGame {

    private int player1_score = 0;
    private int player2_score = 0;
    private String player1;
    private String player2;

    public TennisGame(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1)) {
            player1_score += 1;
        } else {
            player2_score += 1;
        }
    }

    public String getScore() {
        if (player1_score == player2_score) {
            return scoreIsEven();

        } else if (player1_score >= 4 || player2_score >= 4) {
            return atleastOnePlayerHasFourPoints();

        } else {
            return onePlayerIsInLeadWithUnderFourPoints();
        }
    }

    public String scoreIsEven() {
        String score = "";
        switch (player1_score) {
            case 0:
                score = "Love-All";
                break;
            case 1:
                score = "Fifteen-All";
                break;
            case 2:
                score = "Thirty-All";
                break;
            case 3:
                score = "Forty-All";
                break;
            default:
                score = "Deuce";
                break;

        }
        return score;
    }

    public String atleastOnePlayerHasFourPoints() {
        String score = "";
        int gap = player1_score - player2_score;
        if (gap == 1) {
            score = "Advantage player1";
        } else if (gap == -1) {
            score = "Advantage player2";
        } else if (gap >= 2) {
            score = "Win for player1";
        } else {
            score = "Win for player2";
        }
        return score;
    }

    public String onePlayerIsInLeadWithUnderFourPoints() {
        String score = "";
        int tempScore = 0;

        for (int i = 1; i < 3; i++) {
            if (i == 1) {
                tempScore = player1_score;
            } else {
                score += "-";
                tempScore = player2_score;
            }
            switch (tempScore) {
                case 0:
                    score += "Love";
                    break;
                case 1:
                    score += "Fifteen";
                    break;
                case 2:
                    score += "Thirty";
                    break;
                case 3:
                    score += "Forty";
                    break;
            }
        }
        return score;
    }
}
