package camp;

import camp.model.Student;
import camp.model.Subject;

import java.util.Scanner;

public class CreateStudent {

    public void createStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n<수강생을 등록>");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();

        // 수강생 상태 등록 리펙토링
        String studentCondition = displayStudentCondition();

        // 수강생 상태 기입란 추가
        Student student = new Student(CampManagementApplication.sequence("ST"), studentName, studentCondition); // 수강생 인스턴스 생성 예시 코드
        // 기능 구현
        CampManagementApplication.getStudentStore().put(student.getStudentId(), student.getStudent());


        System.out.println("수강가능 과목: "); // 나중에 수정
        for (Subject s : CampManagementApplication.getSubjectStore()) {
            System.out.println("- " + s.getSubjectName());
        }


        while (true) {
            System.out.print("위에 과목중 수강 할 과목 선택: ");
            String subject = sc.next();


            // 수강과목 추가
            student.setStudentSubject(subject);


            System.out.println("더 추가하시겠습니까? 1:예 2:아니오, 숫자로 입력햇주세요");
            String more = sc.next();
            if (more.equals("2"))
                break;
        }


        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 상태입력 메서드
    public String displayStudentCondition() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n수강생 상태 입력 :");
            System.out.println("1. 양호");
            System.out.println("2. 주의");
            System.out.println("3. 위험");
            System.out.print("관리 항목을 선택하세요...");
            int input;

            // 숫자 입력 예외처리
            try {
                input = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                // 숫자외 이상한 값 입력시 입력 오류
                System.out.println("\n입력오류: 정해진 양식대로 입력해주세요.");
                continue;
            }
            switch (input) {
                case 1:
                    return "Green";   // 양호
                case 2:
                    return "Yellow";  // 주의
                case 3:
                    return "Red";     // 위험
                default:
                    System.out.println("잘못된 입력입니다.\n다시 입력해주세요.\n");
            }
        }
    }
}
