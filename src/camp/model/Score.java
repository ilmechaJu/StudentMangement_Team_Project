package camp.model;

public class Score {

    private int score;
    private int round;
    private char grade;

    public Score(int score, int round) {
        this.score = score;
        this.round = round;
        //등급제작
    }

    public int getScore() {
        return score;
    }
}
