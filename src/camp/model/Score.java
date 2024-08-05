package camp.model;

public class Score {

    private int score;
    private int round;
    private char grade;
    private Subject subject;

    public Score(int score, int round, Subject subject) {
        this.score = score;
        this.round = round;
        this.subject = subject;
        if (this.subject.getSubjectType().equals("MANDATORY")) {
            grade = mandatorySubjectGrade(score);
        } else if (this.subject.getSubjectType().equals("CHOICE")) {
            grade = choiceSubjectGrade(score);
        }
    }

    public Score(int score, Subject subject) {
        this.score = score;
        this.subject = subject;
        if (this.subject.getSubjectType().equals("MANDATORY")) {
            grade = mandatorySubjectGrade(score);
        } else if (this.subject.getSubjectType().equals("CHOICE")) {
            grade = choiceSubjectGrade(score);
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
        grade = mandatorySubjectGrade(score);
    }

    private char mandatorySubjectGrade(double score) {
        char result = 'N';

        if (score >= 95) {
            result = 'A';
        } else if (score >= 90) {
            result = 'B';
        } else if (score >= 80) {
            result = 'C';
        } else if (score >= 70) {
            result = 'D';
        } else if (score >= 60) {
            result = 'F';
        } else if (score < 60) {
            result = 'N';
        }

        return result;
    }

    private char choiceSubjectGrade(double score) {
        char result = 'N';

        if (score >= 90) {
            result = 'A';
        } else if (score >= 80) {
            result = 'B';
        } else if (score >= 70) {
            result = 'C';
        } else if (score >= 60) {
            result = 'D';
        } else if (score >= 50) {
            result = 'F';
        } else if (score < 50) {
            result = 'N';
        }

        return result;
    }

}
