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


        //추가가 잘 되었는지 확인합니다.
//        System.out.println("setStudentSubject funtion is done! below is the map keys");
//        for (Subject k : subjectScores.keySet()) {
//            System.out.println(k.getSubjectName());
//        }
    }

    /*public void setScore() {
        Scanner sc = new Scanner(System.in);

        System.out.println("과목명을 입력하세요");
        String subjectName = sc.next();

        for (Subject subject : subjectStore) {
            if (subject.getSubjectName().equals(subjectName)) {
                System.out.println("회차를 입력하세요");
                int subjectRound = sc.nextInt();
                System.out.println("점수를 입력하세요");
                int subjectScore = sc.nextInt();

                Score score = new Score(subjectScore, subjectRound);
                if (this.getSubjectScores().containsKey(subject)) {
                    this.getSubjectScores().get(subject).add(score);
                }
            }
        }

//        System.out.println("funtion is done! below is the map keys");
//        for (Map.Entry<Subject, List<Score>> entry : subjectScores.entrySet()) {
//            Subject subject = entry.getKey();
//            List<Score> scores = entry.getValue();
//            System.out.println("Subject: " + subject.getSubjectName());
//            for (Score score : scores) {
//                System.out.println("Score: " + score.getScore());
//            }
//        }
    }*/

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
