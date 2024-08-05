package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.List;
import java.util.Scanner;

public class InquireSpecificStatusStudentMandatory {
    public void InquireSpecificStatusStudentMandatory() {
        Scanner sc = new Scanner(System.in);
        List<Student> studentList = CampManagementApplication.getStudentStore().values().stream().toList();
        System.out.println("조회할 특정상태를 입력해주세요...");
        String condition = sc.next();

        List<Student> selectedStudent = studentList.stream().filter(student -> student.getCondition().equals(condition)).toList();

        for (Student student : selectedStudent) {
            List<Subject> subjects = student.getSubjectScores().keySet().stream().toList();
            List<Subject> mandatorySubjects = subjects.stream().filter(subject -> subject.getSubjectType().equals("MANDATORY")).toList();
            for (Subject mandatorySubject : mandatorySubjects) {
                List<Score> scores = student.getSubjectScores().get(mandatorySubject);
                double sum = 0;
                for (Score score : scores) {
                    sum += score.getScore();
                }
                System.out.println(student.getStudentName() + "의 " + "Java" + "평균: " + sum / scores.size());
            }
        }
    }
}
