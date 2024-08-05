package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.List;
import java.util.Scanner;

public class InquireSpecificStatusStudentMandatory {
    public void InquireSpecificStatusStudentMandatory() {
        Scanner sc = new Scanner(System.in);
        //studentStore의 value인 Student 클래스들만 리스트로 가져옴
        List<Student> studentList = CampManagementApplication.getStudentStore().values().stream().toList();
        System.out.println("조회할 특정상태를 입력해주세요...");
        String condition = sc.next();

        //입력받은 컨디션과 일치하는 학생들만 리스트로 가져옴
        List<Student> selectedStudent = studentList.stream().filter(student -> student.getCondition().equals(condition)).toList();

        for (Student student : selectedStudent) {
            //학생이 고른 과목들만 리스트로 가져옴
            List<Subject> subjects = student.getSubjectScores().keySet().stream().toList();
            //학생이 고른 과목중 필수 과목만 리스트로 가져옴
            List<Subject> mandatorySubjects = subjects.stream().filter(subject -> subject.getSubjectType().equals("MANDATORY")).toList();
            //학생이 고른 필수과목을 순회하면서
            for (Subject mandatorySubject : mandatorySubjects) {
                List<Score> scores = student.getSubjectScores().get(mandatorySubject);//각각의 필수 과목의 Score 클래스들의 리스트를 구현체로 만듬
                double sum = 0;
                for (Score score : scores) {
                    sum += score.getScore();
                }
                Score score = new Score();//Score 클래스 안의 sumToGrade 메소드를 사용하기 위한 껍데기
                System.out.println(student.getStudentName() + "의 " + mandatorySubject.getSubjectName() + "의 평균등급: " + score.sumToGrade(sum / scores.size(), mandatorySubject));
            }
        }
    }
}
