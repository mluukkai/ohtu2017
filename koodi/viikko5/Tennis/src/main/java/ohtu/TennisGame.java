package ohtu;

public class TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    
    private static String player1Name = "player1";
    private static int matchPointThreshold = 4;
    private static int advantageThreshold = 1;
    private static int winThreshold = 2;
    
    private static final int love = 0;
    private static final int fifteen = 1;
    private static final int thirty = 2;
    private static final int forty = 3;

    public void wonPoint(String playerName) {

        if (playerName == player1Name) {
            m_score1 += 1;
        } else {
            m_score2 += 1;
        }
    }

    public String getScore() {

        String score = "";
        int tempScore;
        if (m_score1 == m_score2) {
            switch (m_score1) {
                case love:
                    score = "Love-All";
                    break;
                case fifteen:
                    score = "Fifteen-All";
                    break;
                case thirty:
                    score = "Thirty-All";
                    break;
                case forty:
                    score = "Forty-All";
                    break;
                default:
                    score = "Deuce";
                    break;
            }
        } else if (m_score1 >= matchPointThreshold || m_score2 >= matchPointThreshold) {
            int minusResult = m_score1 - m_score2;
            if (minusResult == advantageThreshold) {
                return "Advantage player1";
            } else if (minusResult == -advantageThreshold) {
                return "Advantage player2";
            } else if (minusResult >= winThreshold) {
                return "Win for player1";
            } else {
                return "Win for player2";
            }
        } else {
            for (int i = 1; i < 3; i++) {
                if (i == 1) {
                    tempScore = m_score1;
                } else {
                    score += "-";
                    tempScore = m_score2;
                }
                switch (tempScore) {
                    case love:
                        score += "Love";
                        break;
                    case fifteen:
                        score += "Fifteen";
                        break;
                    case thirty:
                        score += "Thirty";
                        break;
                    case forty:
                        score += "Forty";
                        break;
                }
            }
        }
        return score;
    }
}
