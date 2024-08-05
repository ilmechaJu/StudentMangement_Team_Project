package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.*;
import java.util.stream.Collectors;

public class InquireGrade {

    public void inquireRoundGradeBySubject() {
        Scanner sc = new Scanner(System.in);

        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        String studentId = "";
        try {
            studentId = "ST" + String.valueOf(sc.nextInt());// 관리할 수강생 고유 번호
        } catch (InputMismatchException e) {
            System.out.println("숫자만 입력 가능합니다...");
            return;
        }

        //입력받은 Id의 학생이 등록되어 있는지 확인
        if (CampManagementApplication.getStudentStore().containsKey(studentId)) {
            Student student = CampManagementApplication.getStudentStore().get(studentId);//입력받은 Id를 가진 학생 클래스를 가져옴
            boolean check = true;//등록된 과목인지 확인

            student.displaySubjects();
            System.out.println("조회할 과목을 입력하세요...");
            int subjectNumber = 0;
            try {
                subjectNumber = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("숫자만 입력 가능합니다...");
                return;
            }

            List<Subject> selectedSubjects = student.getSubjectScores().keySet().stream().toList();//수강생이 등록한 과목들만 리스트로
            //수강생이 등록한 과목들을 순회하면서
            for (Subject selectedSubject : selectedSubjects) {
                //입력한 과목과 일치하는 경우
                if (selectedSubject.getSubjectId().equals(String.valueOf("SU" + subjectNumber))) {
                    check = false;
                    System.out.println("Subject: " + selectedSubject.getSubjectName());
                    //입력한 과목과 일치하는 과목명을 가진 클래스를 키값으로 회차와 점수를 가지는 value인 Score클래스들의 리스트를 가져옴
                    List<Score> scores = student.getSubjectScores().get(selectedSubject);
                    Map<Integer, Character> map = new HashMap<>();//회차와 등급을 넣기 위한 맵
                    //회차와 등급을 맵에 넣어줌
                    for (Score score : scores) {
                        map.put(score.getRound(), score.getGrade());
                    }
                    //회차를 기준으로 오름차 정렬
                    List<Map.Entry<Integer, Character>> sortedEntries = map.entrySet()
                            .stream()
                            .sorted(Map.Entry.comparingByKey())
                            .toList();
                    if (scores.isEmpty()) {
                        System.out.println("등록된 회차와 점수가 없습니다...");
                    } else {
                        //정렬된 리스트를 출력
                        sortedEntries.forEach(entry -> System.out.println("회차: " + entry.getKey() + ", 등급: " + entry.getValue()));
                        System.out.println("등급 조회 성공!");
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
    }
}
