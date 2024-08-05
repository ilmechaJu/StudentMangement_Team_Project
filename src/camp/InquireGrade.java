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

        //입력받은 Id의 학생이 등록되어 있는지 확인
        if (CampManagementApplication.getStudentStore().containsKey(studentId)) {
            Student student = CampManagementApplication.getStudentStore().get(studentId);//입력받은 Id를 가진 학생 클래스를 가져옴
            boolean check = true;//등록된 과목인지 확인
            List<Subject> selectedSubjects = student.getSubjectScores().keySet().stream().toList();//수강생이 등록한 과목들만 리스트로
            //수강생이 등록한 과목들을 순회하면서
            for (Subject selectedSubject : selectedSubjects) {
                //입력한 과목과 일치하는 경우
                if (selectedSubject.getSubjectName().equals(subjectName)) {
                    check = false;
                    System.out.println("Subject: " + selectedSubject.getSubjectName());
                    //입력한 과목과 일치하는 과목명을 가진 클래스를 키값으로 회차와 점수를 가지는 value인 Score클래스들의 리스트를 가져옴
                    List<Score> scores = student.getSubjectScores().get(selectedSubject);
                    if (scores.isEmpty()) {
                        System.out.println("등록된 회차와 점수가 없습니다...");
                    } else {
                        for (Score score : scores) {
                            System.out.println("Round: " + score.getRound() + " Grade: " + score.getGrade());
                        }
                        break;
                    }
                }
            }
            if (check) {
                System.out.println("수강생이 등록한 과목이 아닙니다...");
            }
        } else {
            System.out.println("등록되지 않은 학생입니다...");
        }

        System.out.println("\n등급 조회 성공!");
    }
}
