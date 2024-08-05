package camp;

import camp.model.Student;
import camp.model.Subject;

import java.util.Scanner;

public class InquireStudent {

    public void inquireStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n수강생 목록을 조회합니다...");

//        String studentId = sc.next();
//
//        Student student = studentStore.get(studentId);

        // 기능 구현
        for (Student student : CampManagementApplication.getStudentStore().values()) {
            System.out.println(student.getStudentId() + " " + student.getStudentName());
        }
        System.out.println("\n수강생 목록 조회 성공!");

        // 정보조회 기능 구현
        System.out.println("수강생 정보를 조회하시겠습니까? (y / n)");
        String inputInfo = sc.next();
        if (inputInfo.equals("y")) {
            System.out.print("ST넘버를 선택하세요... 예)'ST1', 'ST2'...");
            String inputST = sc.next();
            System.out.println(CampManagementApplication.getStudentStore().containsKey(inputST));
            if (CampManagementApplication.getStudentStore().containsKey(inputST)) {
                Student selectedStudent = CampManagementApplication.getStudentStore().get(inputST); // ST넘버로 특정 학생 객체 가져오기
                System.out.println(inputST + "의 수강생 정보는 다음과 같습니다");
                System.out.println("학생 ID: " + selectedStudent.getStudentId());
                System.out.println("학생 이름: " + selectedStudent.getStudentName());
                System.out.println("학생 상태: " + selectedStudent.getCondition());
                //System.out.println("선택과목명: " + selectedStudent.getSubjectName());
                System.out.println("==============신청과목명==============");
                for (Subject s : selectedStudent.getSubjectScores().keySet()){
                    System.out.println(s.getSubjectName());
                }

            } else {
                System.out.print("존재하지 않는 ST넘버입니다. 수강생 목록에 있는 ST넘버를 입력해주세요.");
            }
        }


    }
}
