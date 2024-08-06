package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.List;

public class InquireAverageGrade {

    public void inquireAverageGrade() {
        String studentId = CampManagementApplication.getStudentId();
        Student student = CampManagementApplication.getStudentStore().get(studentId);
        System.out.println(student.getStudentName());

        int sum;
        int cnt;

        for(Subject subject : student.getSubjectScores().keySet()) {
            List<Score> scores = student.getSubjectScores().get(subject);
            cnt = 0;
            sum = 0;
            for(Score score : scores) {
                sum+=score.getScore();
                cnt++;
            }
            try {
                System.out.println(subject.getSubjectName());
                System.out.println(sum/cnt);
                System.out.println(new Score(sum/cnt,subject).getGrade());
                System.out.println("----------------------------");
            }catch(ArithmeticException e) {
                System.out.println("시험 응시 기록이 없는 과목입니다.");
                System.out.println("----------------------------");
            }
        }

    }
}
