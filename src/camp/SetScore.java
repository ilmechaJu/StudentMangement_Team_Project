package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.Scanner;

public class SetScore {
    private Student student;

    public SetScore(Student student) {
        this.student = student;
    }

    public void setScore() {
        Scanner sc = new Scanner(System.in);

        System.out.println("과목명을 입력하세요");
        String subjectName = sc.next();

        for (Subject subject : CampManagementApplication.getSubjectStore()) {
            if (subject.getSubjectName().equals(subjectName)) {
                System.out.println("회차를 입력하세요");
                int subjectRound = sc.nextInt();
                System.out.println("점수를 입력하세요");
                int subjectScore = sc.nextInt();

                Score score = new Score(subjectScore, subjectRound, subject);
                if (student.getSubjectScores().containsKey(subject)) {
                    student.getSubjectScores().get(subject).add(score);
                }
            }
        }

//        System.out.println("funtion is done! below is the map keys");
//        for (Map.Entry<Subject, List<Score>> entry : subjectScores.entrySet()) {
//            Subject subject = entry.getKey();
//            List<Score> scores = entry.getValue();
//            System.out.println("Subject: " + subject.getSubjectName());
//            for (Score score : scores) {
//                System.out.println("Score: " + score.getScore());
//            }
//        }
    }
}
