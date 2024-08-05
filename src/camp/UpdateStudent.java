package camp;

import camp.model.Student;

import java.util.Scanner;

public class UpdateStudent {

    public void updateStudent() {
        Scanner sc = new Scanner(System.in);
        CreateStudent createStudent = new CreateStudent();
        if (!CampManagementApplication.getStudentStore().isEmpty()) {
            boolean flag = true;
            while (flag) {
                System.out.println("\n수정할 수강생의 ID를 입력하세요 :");
                String studentId = sc.next();
                Student student = CampManagementApplication.getStudentStore().get(studentId);

                System.out.println("이름과 상태를 확인해 주세요.");
                System.out.println("이름 : " + student.getStudentName());
                System.out.println("상태 : " + student.getCondition());
                System.out.println("\n수정하시겠습니까?");
                System.out.println("1. 예");
                System.out.println("2. 아니요");
                int input = sc.nextInt();
                if (input == 2) {
                    flag = false;
                    break;
                }
                if (input == 1) {
                    System.out.println("수강생 이름 : " + student.getStudentName());
                    System.out.print("\n수강생 이름 수정 :");
                    String studentName = sc.next();
                    student.setStudentName(studentName);
                    System.out.println("수강생 상태 : " + student.getCondition());
                    System.out.print("\n수강생 상태 수정");
                    student.setStudentCondition(createStudent.displayStudentCondition());
                    System.out.println("수정완료.");
                    System.out.println("수정된 이름 : " + student.getStudentName());
                    System.out.println("수정된 상태 : " + student.getCondition());
                    flag = false;
                    break;
                }
                System.out.println("잘못된 입력입니다.\n다시 입력해주세요.\n");
            }
        } else {
            System.out.println("수정할 수강생의 정보가 없습니다.");
        }
    }
}