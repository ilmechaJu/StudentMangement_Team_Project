package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SetScore {
    private Student student;

    public SetScore(Student student) {
        this.student = student;
    }

    public void setScore() {
        Scanner sc = new Scanner(System.in);

        int subjectRound;
        int subjectScore = 0;
        int subjectNumber = 0;
        Subject enterdSubject;

        System.out.println("과목 리스트");
        List<Subject> subjectStore = CampManagementApplication.getSubjectStore();
        for (Subject s : subjectStore) {
            System.out.println(s.getSubjectId().charAt(2) + ". " + s.getSubjectName());
        }

        while (true) {
            //과목번호를 통해 과목 입력받기.
            while (true) {
                System.out.println("과목번호를 입력하세요");
                try { //숫자만 받도록 예외처리
                    subjectNumber = sc.nextInt();
                    if (subjectNumber < 1 || subjectNumber > 9) System.out.println("잘못된 입력입니다. 범위 내 값을 입력해주세요(1~9)");
                    else break;
                } catch (InputMismatchException e) {
                    System.out.println("입력오류: 숫자만 입력해주세요.");
                    sc.next();
                }
            }

            enterdSubject = subjectStore.get(subjectNumber - 1);

            //수강한 과목인지 확인
            if (student.getSubjectScores().containsKey(enterdSubject)) {
                if (student.getSubjectScores().get(enterdSubject).size() == 10) { //이미 10회를 치른 시험인지 확인
                    System.out.println("이미 10회 시험을 치른 과목입니다.");
                    return; //메소드 종료
                } else {
                    // 해당 과목 시험 회차 입력하기
                    System.out.println("회차를 입력하세요");
                    loop1:
                    while (true) {
                        try { //숫자만 받도록 예외처리
                            subjectRound = sc.nextInt();
                            if (subjectRound < 1 || subjectRound > 10) {
                                System.out.println("잘못된 입력입니다. 범위 내 값을 입력해주세요(1~10)");
                            } else {
                                //스코어 객체 순회하면서 해당 회차가 있는지 확인 시작
                                List<Score> scores = student.getSubjectScores().get(enterdSubject);
                                for (Score score : scores) {
                                    if (score.getRound() == subjectRound) {
                                        System.out.println("이미 등록된 회차입니다. 다시 입력해주세요.");
                                        continue loop1;
                                    }
                                }
                                break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("입력오류: 숫자만 입력해주세요.");
                            sc.next();
                        }
                    }

                    System.out.println("점수를 입력하세요");
                    while (true) {
                        try { //숫자만 받도록 예외처리
                            subjectScore = sc.nextInt();
                            if (subjectScore < 0 || subjectScore > 100)
                                System.out.println("잘못된 입력입니다. 범위 내 값을 입력해주세요(0~100)");
                            else break;
                        } catch (InputMismatchException e) {
                            System.out.println("입력오류: 숫자만 입력해주세요.");
                            sc.next();
                        }
                    }
                    break;
                }
            } else { //미수강 과목이라면
                System.out.println("미수강 과목입니다. 다시 입력해주세요.");
            }
        }

        Score score = new Score(subjectScore, subjectRound);
        student.getSubjectScores().get(enterdSubject).add(score);
        System.out.println("\n점수 등록 성공!");
    }
}