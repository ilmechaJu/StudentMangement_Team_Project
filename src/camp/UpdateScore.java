package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UpdateScore {

    // 수강생의 과목별 회차 점수 수정
    public void updateRoundScoreBySubject() {
        Scanner sc = new Scanner(System.in);

        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        String studentId = "";
        try {
            studentId = "ST" + String.valueOf(sc.nextInt());// 관리할 수강생 고유 번호
        } catch (InputMismatchException e) {
            System.out.println("숫자만 입력 가능합니다...");
            return;
        }

        //입력받은 수강생이 등록되어 있는지 확인
        if (CampManagementApplication.getStudentStore().containsKey(studentId)) {
            Student student = CampManagementApplication.getStudentStore().get(studentId);//student studentStore의
            // 기능 구현 (수정할 과목 및 회차, 점수)
            System.out.println("시험 점수를 수정합니다...");
            System.out.println("수정할 과목을 입력하세요...");
            String subjectName = sc.next();

            boolean check1 = true;//수강생이 등록한 과목인지 확인
            List<Subject> selectedSubjects = student.getSubjectScores().keySet().stream().toList();//수강생이 등록한 과목들만 리스트로
            //수강생이 등록한 과목들의 리스트를 순회하며
            for (Subject selectedSubject : selectedSubjects) {
                //수정할 과목명과 일치하는 Subject 클래스를 찾음
                if (selectedSubject.getSubjectName().equals(subjectName)) {
                    check1 = false;
                    System.out.println("Subject: " + selectedSubject.getSubjectName());
                    System.out.println("점수를 수정할 회차를 입력하세요...");
                    int round = sc.nextInt();
                    boolean check2 = true;//수정할 회차가 등록된 회차인지 확인
                    //찾아낸 Subject 클래스를 키값으로 사용하여 List<Score> value를 찾아서 순회
                    for (Score score : student.getSubjectScores().get(selectedSubject)) {
                        //List<Score>를 순회하며 Score 클래스의 회차가 입력받은 회차와 일치하는지 확인
                        if (score.getRound() == round) {
                            check2 = false;
                            System.out.println("새로운 점수를 입력하세요...");
                            int scoreToEdit = sc.nextInt();
                            System.out.println("수정하기 전 점수");
                            System.out.println("Round: " + score.getRound() + " Score: " + score.getScore());
                            score.setScore(scoreToEdit);//크리스탈
                            System.out.println("수정한 점수");
                            System.out.println("Round: " + score.getRound() + " Score: " + score.getScore());
                            System.out.println("점수 수정 성공!");
                        }
                    }
                    if (check2) {
                        System.out.println("등록되지 않은 회차입니다...");
                    }
                }
            }
            if (check1) {
                System.out.println("등록되지 않은 과목입니다...");
            }
        } else {
            System.out.println("등록되지 않은 학생입니다...");
        }
    }
}
