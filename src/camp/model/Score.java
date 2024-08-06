package camp.model;

public class Score {

    private int score;
    private int round;
    private char grade;

    public Score(int score, int round, Subject subject) {
        this.score = score;
        this.round = round;
        if (subject.getSubjectType().equals("MANDATORY")) {
            grade = mandatorySubjectGrade(score);
        } else if (subject.getSubjectType().equals("CHOICE")) {
            grade = choiceSubjectGrade(score);
        }
    }

    public Score(int score, Subject subject) {
        this.score = score;
        if (subject.getSubjectType().equals("MANDATORY")) {
            grade = mandatorySubjectGrade(score);
        } else if (subject.getSubjectType().equals("CHOICE")) {
            grade = choiceSubjectGrade(score);
        }
    }

    public Score() {

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

    public char sumToGrade(double sum, Subject subject) {
        if (subject.getSubjectType().equals("MANDATORY")) {
            return mandatorySubjectGrade(sum);
        } else {
            return choiceSubjectGrade(sum);
        }
    }

    private char mandatorySubjectGrade(double score) {
        if (score >= 95) {
            return 'A';
        } else if (score >= 90) {
            return 'B';
        } else if (score >= 80) {
            return 'C';
        } else if (score >= 70) {
            return 'D';
        } else if (score >= 60) {
            return 'F';
        } else {
            return 'N';
        }
    }

    private char choiceSubjectGrade(double score) {
        if (score >= 90) {
            return 'A';
        } else if (score >= 80) {
            return 'B';
        } else if (score >= 70) {
            return 'C';
        } else if (score >= 60) {
            return 'D';
        } else if (score >= 50) {
            return 'F';
        } else {
            return 'N';
        }
    }

}
