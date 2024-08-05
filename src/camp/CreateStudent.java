package camp;

import camp.model.Student;
import camp.model.Subject;

import java.util.*;

public class CreateStudent {

    public void createStudent() {
        Scanner sc = new Scanner(System.in);
        Queue<Integer> enrolledSubjects = new LinkedList<>();
        System.out.println("\n<수강생을 등록>");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();
        boolean enrollment = true;

        // 수강생 상태 등록 리펙토링
        String studentCondition = displayStudentCondition();

        // 수강생 상태 기입란 추가
        Student student = new Student(CampManagementApplication.sequence("ST"), studentName, studentCondition); // 수강생 인스턴스 생성 예시 코드
        // 기능 구현
        // StudentStore 리스트에 student 추가
        CampManagementApplication.getStudentStore().put(student.getStudentId(), student.getStudent());

        // 유저분들이 볼 수 있게 수강과목 리스트 츨력
        System.out.println("수강가능 과목들(필수 최소 3개, 선택 최소 2개): ");
        List<Subject> subjectStore = CampManagementApplication.getSubjectStore();
        for (Subject s : subjectStore) {
            if (s.getSubjectType().equals("MANDATORY"))
                System.out.println(s.getSubjectId().charAt(2) + ". " + s.getSubjectName() + " (필수)");
            else
                System.out.println(s.getSubjectId().charAt(2) + ". " + s.getSubjectName() + " (선택)");
        }
        // 수강신청 동안 계속 도는 루프
        while (enrollment) {
            System.out.print("위에 과목중 수강 할 과목들을 ',' 구분해 선택해주세요.");
            System.out.println("(예: 1,2,3,6,7)");
            System.out.print("수강신청: ");
            String subject = sc.next();
            // 일단 받은 string입력 값을 string array에 나눠서 과목을 구분 해줍니다.
            String[] subjectNumberArray = subject.split(",");
            // 제대로된 과목인지 잘 체크하고 학생의 scoredSubject map에 더 쉽게 올리기 위해
            // enrolledSubjects라는 Queue 로 데이터를 복사해 갑니다.
            for (String subjectNumbers : subjectNumberArray) {
                int subjectNumber = 0;
                try {
                    // 복사해 갈대 String인 수강신청 입력값을 int로 변환하기
                    subjectNumber = Integer.parseInt(subjectNumbers.trim());
                } catch (NumberFormatException e) {
                    // 숫자외 이상한 값 입력시 입력 오류
                    System.out.println("입력오류: 정해진 양식대로 수강신청을 해주세요.");
                    // 이번 회차 입력받은 다른 과목들도 (있다면) 추가하지 않도록 queue 비우기
                    while (!enrolledSubjects.isEmpty())
                        enrolledSubjects.poll();
                    break; // 입력값이 잘못됬으므로 수강신청 루프를 나갑니다.
                }
                if (subjectNumber > 9 || subjectNumber < 1) {
                    System.out.println("오류: 수강불가한 과목이 포함된 수강신청 입니다.");
                    while (!enrolledSubjects.isEmpty())
                        enrolledSubjects.poll();
                    break;
                }
                // 수강과목 queue 에추가
                enrolledSubjects.add(subjectNumber);
            }
            // 수강과목 추가
            // 위 수강신청 루프에서 제대로 수강신청이 되었다면 enrolledStubjects가 비어있지 않으므로 루프가 돌아간다
            while (!enrolledSubjects.isEmpty())
            {
                // 입력된 과목번호에서 -1을 하면 subjectStore에서의 같은과목 index번호와 일치한다
                Subject sbj = subjectStore.get(enrolledSubjects.poll()-1);
                // 이렇게 가져온 과목을 학생 객체에 추가!
                student.setStudentSubject(sbj.getSubjectName());
            }
            // 고른과목 보여주기
            System.out.println("현재 등록된 과목(들):");
            System.out.println("-------------------------------------------");
            student.displaySubjects();

            while (true) {
                System.out.println("수강과목을 더 추가하시겠습니까? 1:계속추가, 2:끝네기");
                String more = sc.next();
                if (more.equals("2")) {
                    // 필수3개, 선택2개 과목 제대로 선택 했는지 확인
                    if (student.enrollmentValid()) {
                        enrollment = false;
                        break;
                    }
                    else
                        System.out.println("수강신청과목이 필수과목 3개, 선택과목 2개를 충족하지 않습니다. 과목을 추가해 주세요!");
                    // 나중에 수강생 삭제 기능 구현후, 수강생 등록 포기 기능 추가

                    System.out.println("============== 과목조건 미달로인한 추가신청 ==============");
                    System.out.println("수강가능 과목들(필수 최소 3개, 선택 최소 2개): "); // 나중에 수정
                    for (Subject s : CampManagementApplication.getSubjectStore()) {
                        if (s.getSubjectType().equals("MANDATORY"))
                            System.out.println(s.getSubjectId().charAt(2) + ". " + s.getSubjectName() + " (필수)");
                        else
                            System.out.println(s.getSubjectId().charAt(2) + ". " + s.getSubjectName() + " (선택)");
                    }
                    break;
                } else if (more.equals("1"))
                {
                    break;
                }
                else
                {
                    System.out.println("입력오류 입니다. 1 또는 2 만 입력 가능합니다.");
                    continue;
                }

            }
        }
        System.out.println("수강신청 성공!^^");
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
