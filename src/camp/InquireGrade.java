package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InquireGrade {

    public void inquireRoundGradeBySubject() {
        Scanner sc = new Scanner(System.in);
        String studentId = CampManagementApplication.getStudentId(); // 관리할 수강생 고유 번호

        System.out.println("조회할 과목을 입력하세요...");
        String subjectName = sc.next();

        for (Student student : CampManagementApplication.getStudentStore().values()) {
            int cnt = 0;
            if (student.getStudentId().equals(studentId)) {
                for (Map.Entry<Subject, List<Score>> entry : student.getSubjectScores().entrySet()) {
                    Subject subject = entry.getKey();
                    if (subject.getSubjectName().equals(subjectName)) {
                        cnt++;
                        System.out.println("Subject: " + subject.getSubjectName());
                        List<Score> scores = entry.getValue();
                        for (Score score : scores) {
                            System.out.println("Round: " + score.getRound() + " Grade: " + score.getGrade());
                        }
                        break;
                    }
                }
                if (cnt == 0) {
                    System.out.println("수강생이 등록한 과목이 아닙니다...");
                }
                break;
            }
        }
        System.out.println("\n등급 조회 성공!");
    }
}
