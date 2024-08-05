package camp.model;

import camp.CampManagementApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    private String studentId;
    private String studentName;
    // 수강생 상태 추가
    public String studentCondition;
    private Map<Subject, List<Score>> subjectScores = new HashMap<>();


    // 수강생 기입 추가
    public Student(String seq, String studentName, String studentCondition) {
        this.studentId = seq;
        this.studentName = studentName;
        this.studentCondition = studentCondition;
    }

    // 수강할 과목 맵에 넣기
    public void setStudentSubject(String subjectName) {
        for (Subject subject : CampManagementApplication.getSubjectStore()) {
            if (subjectName.equals(subject.getSubjectName())) {
                if (!subjectScores.containsKey(subject)) {
                    // If not, create a new list and put it in the map
                    subjectScores.put(subject, new ArrayList<Score>());
                } else {
                    System.out.println("이미 수강신청된 과목입니다.");
                }
            }
        }

    }

    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    // 수강생 상태 Getter 메서드 추가
    public String getCondition() {
        return studentCondition;
    }

    public Student getStudent() {
        return this;
    }

    public Map<Subject, List<Score>> getSubjectScores() {
        return subjectScores;
    }

    public void displaySubjects() {
        for (Subject s : subjectScores.keySet()) {
            System.out.println("[ "+s.getSubjectId().charAt(2) + " ] " + s.getSubjectName());
        }
    }

    public boolean enrollmentValid() {
        int mandatory = 0;
        int elective = 0;
        for (Subject s : subjectScores.keySet()) {
            if (s.getSubjectType().equals("MANDATORY"))
                mandatory++;
            else
                elective++;
        }
        return (mandatory>=3 && elective>=2);
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentCondition(String studentCondition) {
        this.studentCondition = studentCondition;
    }
}
