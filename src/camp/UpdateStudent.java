package camp;

import camp.model.Student;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdateStudent {

    public void updateStudent() {
        Scanner sc = new Scanner(System.in);
        CreateStudent createStudent = new CreateStudent();

        if (CampManagementApplication.getStudentStore().isEmpty()) {
            System.out.println("\n수정할 수강생의 정보가 없습니다.\n수강생을 등록해주세요.");
            return;
        }

        while (true) {
            System.out.print("\n수정할 수강생의 ST넘버를 입력하세요(예: 1): ");
            String studentId = "ST" + sc.next();

            if (!CampManagementApplication.getStudentStore().containsKey(studentId)) {
                System.out.println("\n수강생의 ST넘버를 다시 확인해주세요.\n다시 입력해주세요.");
                continue;
            }

            Student student = CampManagementApplication.getStudentStore().get(studentId);
            if (student == null) {
                System.out.println("\n수강생 정보를 찾을 수 없습니다.");
                continue;
            }

            System.out.println("\n이름과 상태를 확인해 주세요.");
            System.out.println("이름: " + student.getStudentName());
            System.out.println("상태: " + student.getCondition());

            int input;
            while (true) {
                System.out.println("\n수정하시겠습니까?");
                System.out.println("1. 예");
                System.out.println("2. 아니요");
                System.out.print("입력(예: 1) : ");

                try {
                    input = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("\n입력오류: 정해진 양식대로 입력해주세요.");
                    sc.next();
                    continue;
                }

                if (input == 1) {
                    System.out.print("수강생 이름 수정: ");
                    student.setStudentName(sc.next());
                    System.out.print("수강생 상태 수정: ");
                    student.setStudentCondition(createStudent.displayStudentCondition());

                    System.out.println("수정완료.");
                    System.out.println("수정된 이름: " + student.getStudentName());
                    System.out.println("수정된 상태: " + student.getCondition());
                    return;
                } else if (input == 2) {
                    System.out.println("수정이 취소되었습니다.");
                    return;
                } else {
                    System.out.println("잘못된 입력입니다.\n다시 입력해주세요.\n");
                }
            }
        }
    }
}