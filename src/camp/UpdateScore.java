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

        String studentId = CampManagementApplication.getStudentId();

        //입력받은 수강생이 등록되어 있는지 확인
        if (CampManagementApplication.getStudentStore().containsKey(studentId)) {
            Student student = CampManagementApplication.getStudentStore().get(studentId);//student studentStore의

            student.displaySubjects();
            System.out.println("조회할 과목을 입력하세요...");
            int subjectNumber = 0;
            try {
                subjectNumber = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("숫자만 입력 가능합니다...");
                return;
            }

            boolean check1 = true;//수강생이 등록한 과목인지 확인
            List<Subject> selectedSubjects = student.getSubjectScores().keySet().stream().toList();//수강생이 등록한 과목들만 리스트로
            //수강생이 등록한 과목들의 리스트를 순회하며
            for (Subject selectedSubject : selectedSubjects) {
                //수정할 과목명과 일치하는 Subject 클래스를 찾음
                if (selectedSubject.getSubjectId().equals(String.valueOf("SU" + subjectNumber))) {
                    check1 = false;
                    System.out.println("Subject: " + selectedSubject.getSubjectName());
                    System.out.println("점수를 수정할 회차를 입력하세요...");
                    boolean check2 = true;//수정할 회차가 등록된 회차인지 확인
                    // 해당 과목 시험 회차 입력하기
                    System.out.println("회차를 입력하세요");
                    try { //숫자만 받도록 예외처리
                        int subjectRound = sc.nextInt();
                        if (subjectRound < 1 || subjectRound > 10) {
                            System.out.println("잘못된 입력입니다. 범위 내 값을 입력해주세요(1~10)");
                        } else {
                            //스코어 객체 순회하면서 해당 회차가 있는지 확인 시작
                            List<Score> scores = student.getSubjectScores().get(selectedSubject);
                            //찾아낸 Subject 클래스를 키값으로 사용하여 List<Score> value를 찾아서 순회
                            for (Score score : student.getSubjectScores().get(selectedSubject)) {
                                //List<Score>를 순회하며 Score 클래스의 회차가 입력받은 회차와 일치하는지 확인
                                if (score.getRound() == subjectRound) {
                                    check2 = false;
                                    System.out.println("새로운 점수를 입력하세요...");
                                    try { //숫자만 받도록 예외처리
                                        int subjectScore = sc.nextInt();
                                        if (subjectScore < 0 || subjectScore > 100)
                                            System.out.println("잘못된 입력입니다. 범위 내 값을 입력해주세요(0~100)");
                                        else {
                                            System.out.println("수정하기 전 점수");
                                            System.out.println("Round: " + score.getRound() + " Score: " + score.getScore());
                                            score.setScore(subjectScore);//크리스탈
                                            System.out.println("수정한 점수");
                                            System.out.println("Round: " + score.getRound() + " Score: " + score.getScore());
                                            System.out.println("점수 수정 성공!");
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("입력오류: 숫자만 입력해주세요.");
                                        sc.next();
                                    }
                                }
                            }
                            if (check2) {
                                System.out.println("등록되지 않은 회차입니다...");
                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("입력오류: 숫자만 입력해주세요.");
                        sc.next();
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
