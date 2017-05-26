package ohtu;

public class TennisGame {
    
    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;
    private String [] points = {"Love","Fifteen","Thirty","Forty"};

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public String getScore() {
        String score = "";
        score = setScore(isEven(),onePlayerHasFortyOrMore(),score);
        return score;
    }
    
    private String setScore(boolean even, boolean fortyOrMore, String score){
        if (even){
            score = getScoreAtEvenCase(m_score1,score);
        }else if (fortyOrMore){
            score = getScoreAtDecivePhase(score);
        }else{
            score = setScoreAtNormalCase(m_score1,m_score2,score); 
        }
        return score;
    } 
    
    private String getScoreAtDecivePhase(String score){
        if (advantage1() || advantage2()){
            score = setAdvantage(score);
        }else {
            score = setWinner(score);
        }
        return score;
    }
    
    private String setAdvantage(String score){
        if (advantage1()){
            score ="Advantage player1";
        }else if (advantage2()){
            score ="Advantage player2";
        }
        return score;
    }
    
    private String setWinner(String score){
        if (scoreDifference()>=2){
            score = "Win for player1";
        }else {
            score ="Win for player2";
        }
        return score;
    }
    
    private String setScoreAtNormalCase(int score1,int score2,String score){
        score = setScore1(score1,score)+ "-";
        score = setScore2(score2,score);
        return score;                
    }
    
    private String setScore1(int score1, String score){
        score += points[score1];
        return score;
    }
    
    private String setScore2(int score1, String score){
        score += points[score1];
        return score;
    }
    
    private String getScoreAtEvenCase(int score1, String score){
        if(onePlayerHasFortyOrMore()){
            score = "Deuce";
        }else{
            score += points[score1] + "-All";
        }        
        return score;
    }
    
    
    private int scoreDifference(){
        return m_score1-m_score2;   
    }
    
    private boolean advantage1(){
        return scoreDifference() == 1;
    }
    
    private boolean advantage2(){
        return scoreDifference() == -1;
    }
    
    private boolean onePlayerHasFortyOrMore(){
        return m_score1>=points.length || m_score2>=points.length;
    }
    
    private boolean isEven(){
        return m_score1==m_score2;
    }
}