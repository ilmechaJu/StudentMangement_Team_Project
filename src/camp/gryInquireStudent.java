package camp;

import camp.model.Student;

import java.util.*;

public class gryInquireStudent {
    public static void gryInquireStudent() {
        Scanner sc = new Scanner(System.in);

        // 조회할 수강생의 정보가 없으면 메서드 종료
        if (CampManagementApplication.getStudentStore().isEmpty()) {
            System.out.println("\n조회할 수강생의 정보가 없습니다.\n수강생을 등록해주세요.");
            return;
        }

        System.out.println("\n상태(GRY)별 수강생 목록을 조회합니다...");

        // 입력예외시 반복
        boolean found = false;
        while (!found) {
            // 상태 선택
            System.out.println("조회하고 싶은 상태 등급을 선택하세요...");
            System.out.println("1. Green (양호)");
            System.out.println("2. Yellow (주의)");
            System.out.println("3. Red (위험)");
            System.out.print("선택: ");
            int input;
            // 입력 숫자 예외처리
            try {
                input = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\n입력오류: 정해진 양식대로 입력해주세요.");
                sc.next();
                continue;
            }
            // 상태에 따른 필터링
            String condition;
            switch (input) {
                case 1 -> condition = "Green";
                case 2 -> condition = "Yellow";
                case 3 -> condition = "Red";
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    continue;
                }
            }

            // 필터링된 결과 출력

            for (Student student : CampManagementApplication.getStudentStore().values()) {
                if (student.getCondition().equalsIgnoreCase(condition)) {
                    System.out.println("학생 ID: " + student.getStudentId() +
                            ", 이름: " + student.getStudentName() +
                            ", 상태: " + student.getCondition());
                    found = true;
                }
            }
            if (!found) {
                System.out.println("선택한 상태의 수강생이 없습니다.\n");
                break;
            }
            System.out.println("\n상태별 수강생 목록 조회 완료!");
        }
    }
}
