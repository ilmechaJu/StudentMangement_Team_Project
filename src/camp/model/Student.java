package camp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    private String studentId;
    private String studentName;
    // 수강생 상태 추가
    private String studentCondition;
    private Map<Subject, List<Score>> subjectScores = new HashMap<>();


    // 수강생 기입 추가
    public Student(String seq, String studentName, String studentCondition) {
        this.studentId = seq;
        this.studentName = studentName;
        this.studentCondition = studentCondition;
    }

    // 수강할 과목 맵에 넣기
    public void setStudentSubject(Subject enteredSubject) {
        if (!subjectScores.containsKey(enteredSubject)) {
            // If not, create a new list and put it in the map
            subjectScores.put(enteredSubject, new ArrayList<Score>());
        } else {
            System.out.println("이미 수강신청된 과목입니다.");
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
        Map<String, Subject> sortedSubject = new HashMap<>();

        for (Subject subject : subjectScores.keySet()) {
            sortedSubject.put(subject.getSubjectId(), subject);
        }

        //인덱스를 기준으로 오름차 정렬
        List<Map.Entry<String, Subject>> sortedEntries = sortedSubject.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .toList();

        sortedEntries.forEach(entry -> System.out.println("[ " + entry.getKey().substring(2) + " ] " + entry.getValue().getSubjectName()));
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentCondition(String studentCondition) {
        this.studentCondition = studentCondition;
    }
}
