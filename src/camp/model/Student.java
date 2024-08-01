package camp.model;
import camp.model.Score;
import camp.model.Subject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static camp.CampManagementApplication.subjectStore;

public class Student {
    private String studentId;
    private String studentName;
    private Map<Subject, List<Score>> subjectScores = new HashMap<>();


    public Student(String seq, String studentName) {
        this.studentId = seq;
        this.studentName = studentName;
    }

    // 수강할 과목 맵에 넣기
    public void setStudentSubject(String subjectName) {
        for (Subject subject : subjectStore) {
            if (subjectName.equals(subject.getSubjectName())) {
                if (!subjectScores.containsKey(subject)) {
                    // If not, create a new list and put it in the map
                    subjectScores.put(subject, new ArrayList<Score>());
                }
                else {
                    System.out.println("이미 수강신청된 과목입니다.");
                }
            }
        }


        // 추가가 잘 되었는지 확인합니다.
//        System.out.println("setStudentSubject funtion is done! below is the map keys");
//        for (Subject k: subjectScores.keySet()) {
//            System.out.println(k.getSubjectName());
//        }
    }

    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }
}
