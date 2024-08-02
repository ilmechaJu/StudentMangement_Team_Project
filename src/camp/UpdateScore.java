package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UpdateScore {

    // 수강생의 과목별 회차 점수 수정
    public void updateRoundScoreBySubject() {
        Scanner sc = new Scanner(System.in);
        String studentId = CampManagementApplication.getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("시험 점수를 수정합니다...");
        System.out.println("수정할 과목을 입력하세요...");
        String subjectName = sc.next();

        for (Student student : CampManagementApplication.getStudentStore().values()) {
            int cnt1 = 0;
            if (student.getStudentId().equals(studentId)) {
                for (Map.Entry<Subject, List<Score>> entry : student.getSubjectScores().entrySet()) {
                    int cnt2 = 0;
                    Subject subject = entry.getKey();
                    if (subject.getSubjectName().equals(subjectName)) {
                        cnt1++;
                        System.out.println("Subject: " + subject.getSubjectName());
                        List<Score> scores = entry.getValue();
                        System.out.println("점수를 수정할 회차를 입력하세요...");
                        int round = sc.nextInt();
                        for (Score score : scores) {
                            if (score.getRound() == round) {
                                cnt2++;
                                System.out.println("새로운 점수를 입력하세요...");
                                int scoreToEdit = sc.nextInt();
                                System.out.println("수정하기 전 점수");
                                System.out.println("Round: " + score.getRound() + " Score: " + score.getScore());
                                score.setScore(scoreToEdit);
                                System.out.println("수정한 점수");
                                System.out.println("Round: " + score.getRound() + " Score: " + score.getScore());
                            }
                        }
                        if (cnt2 == 0) {
                            System.out.println("등록되지 않은 회차입니다...");
                        }
                        break;
                    }
                }
                if (cnt1 == 0) {
                    System.out.println("수강생이 등록한 과목이 아닙니다...");
                }
                break;
            }
        }
        System.out.println("\n점수 수정 성공!");
    }
}
