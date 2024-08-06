package camp;

import camp.model.Student;
import camp.model.Subject;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InquireStudent {

    public void inquireStudent() {

        Scanner sc = new Scanner(System.in);
        while(true) {
            // 수강생 목록 조회 기능
            System.out.println("\n수강생 목록을 조회합니다...");

            // 등록된 수강생이 없을경우 else 출력.
            if (!CampManagementApplication.getStudentStore().isEmpty()) {
                for (Student student : CampManagementApplication.getStudentStore().values()) {
                    System.out.println("학생 ID: " + student.getStudentId()
                            + ", 이름: " + student.getStudentName());
                }
                //System.out.println("\n수강생 목록 조회 성공!");
            }


            // 정보조회 기능 구현
            String inputInfo = "";
            try {
                System.out.println("수강생 정보를 조회하시겠습니까? (y / n)");
                inputInfo = sc.next();
            } catch (InputMismatchException e) {
                System.out.println("\n입력오류: 정해진 양식대로 입력해주세요.");
                sc.next();

            }
            try {
                if (inputInfo.equals("y")) {
                    String inputST = CampManagementApplication.getStudentId();

                    if (CampManagementApplication.getStudentStore().containsKey(inputST)) {
                        Student selectedStudent = CampManagementApplication.getStudentStore().get(inputST); // ST넘버로 특정 학생 객체 가져오기
                        System.out.println(inputST + "의 수강생 정보는 다음과 같습니다");
                        System.out.println("학생 ID: " + selectedStudent.getStudentId());
                        System.out.println("학생 이름: " + selectedStudent.getStudentName());
                        System.out.println("학생 상태: " + selectedStudent.getCondition());
                        System.out.println("==============신청과목명==============");
                        for (Subject s : selectedStudent.getSubjectScores().keySet()) {
                            System.out.println(s.getSubjectName());
                        }
                        break;


                    } else {
                        System.out.print("존재하지 않는 ST넘버입니다. 수강생 목록에 있는 ST넘버를 입력해주세요.");
                    }
                }
                    else if(inputInfo.equals("n")){
                        break;
                    }
                    else {
                    System.out.print("잘못된 입력입니다. y 혹은 n 을 입력해주세요.");
                    }



            } catch (InputMismatchException e) {
                System.out.println("\n입력오류: 정해진 양식대로 입력해주세요.");
                sc.next();
            }
        }
    }
}
