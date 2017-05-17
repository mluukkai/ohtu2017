package ohtu;

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
        if (playerName == player1Name)
            player1Score += 1;
        else if(playerName == player2Name)
            player2Score += 1;
    }

    public String getScore() {
        if (player1Score == player2Score && player1Score <= 3) {
            return getScoreString(player1Score) + "-All";
        } else if(player1Score == player2Score && player1Score > 3) {
            return "Deuce";
        } else if (player1Score >=4 || player2Score >=4) {
            int minusResult = player1Score - player2Score;
            if(minusResult<=-2)         return "Win for player2";
            else if (minusResult ==-1)  return "Advantage player2";
            else if (minusResult==1)    return "Advantage player1";
            else                        return "Win for player1";
        } else {
            return getScoreString(player1Score) + "-" + getScoreString(player2Score);
        }
    }

    private String getScoreString(int score) {
        switch (score) {
            case 0: return "Love";
            case 1: return "Fifteen";
            case 2: return "Thirty";
            case 3: return "Forty";
        }
        return "";
    }
}