package camp.model;

public class Score {

    private int score;
    private int round;
    private char grade;

    public Score(int score, int round) {
        this.score = score;
        this.round = round;

        if (score >= 95) {
            this.grade = 'A';
        } else if (score >= 90) {
            this.grade = 'B';
        } else if (score >= 80) {
            this.grade = 'C';
        } else if (score >= 70) {
            this.grade = 'D';
        } else if (score >= 60) {
            this.grade = 'F';
        } else if (score < 60) {
            this.grade = 'N';
        }
    }

    public int getScore() {
        return score;
    }

    public char getGrade() {
        return grade;
    }

    public int getRound() {
        return round;
    }

    public void setScore(int score) {
        this.score = score;

        if (score >= 95) {
            this.grade = 'A';
        } else if (score >= 90) {
            this.grade = 'B';
        } else if (score >= 80) {
            this.grade = 'C';
        } else if (score >= 70) {
            this.grade = 'D';
        } else if (score >= 60) {
            this.grade = 'F';
        } else if (score < 60) {
            this.grade = 'N';
        }
    }
}
