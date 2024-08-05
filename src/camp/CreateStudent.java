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

        // 수강생 상태 등록 리펙토링
        String studentCondition = displayStudentCondition();

        // 수강생 상태 기입란 추가
        Student student = new Student(CampManagementApplication.sequence("ST"), studentName, studentCondition); // 수강생 인스턴스 생성 예시 코드
        // 기능 구현
        CampManagementApplication.getStudentStore().put(student.getStudentId(), student.getStudent());


        System.out.println("수강가능 과목들(필수 최소 3개, 선택 최소 2개): "); // 나중에 수정
        for (Subject s : CampManagementApplication.getSubjectStore()) {
            if (s.getSubjectType().equals("MANDATORY"))
                System.out.println(s.getSubjectId().charAt(2) + ". " + s.getSubjectName() + " (필수)");
            else
                System.out.println(s.getSubjectId().charAt(2) + ". " + s.getSubjectName() + " (선택)");
        }
        while (true) {
            System.out.print("위에 과목중 수강 할 과목들을 ',' 구분해 선택해주세요.");
            System.out.println("(예: 1,2,3,6,7)");
            System.out.print("수강신청: ");
            String subject = sc.next();
            String[] subjectNumberArray = subject.split(",");
            // enrolledSubjects에 넣기
            for (String subjectNumbers : subjectNumberArray) {
                int subjectNumber = Integer.parseInt(subjectNumbers.trim()); // Convert string to int
                if (subjectNumber>9 || subjectNumber<1)
                {
                    System.out.println("오류: 수강불가한 과목이 포함된 수강신청 입니다.");
                    while (!enrolledSubjects.isEmpty())
                        enrolledSubjects.poll();
                    break;
                }
                enrolledSubjects.add(subjectNumber); // Add the integer to the queue
            }

            // 수강과목 추가
            List<Subject> subjectStore = CampManagementApplication.getSubjectStore();
            while (!enrolledSubjects.isEmpty())
            {
                Subject sbj = subjectStore.get(enrolledSubjects.poll()-1);
                student.setStudentSubject(sbj.getSubjectName());
            }
            // 고른과목 보여주기
            System.out.println("현재 등록된 과목(들):");
            student.displaySubjects();

            System.out.println("더 추가하시겠습니까? 1:예 2:아니오, 숫자로 입력햇주세요");
            String more = sc.next();
            if (more.equals("2")) {
                // 필수3개, 선택2개 과목 제대로 선택 했는지 확인
                if (student.enrollmentValid())
                    break;
                else
                    System.out.println("수강신청과목이 필수과목 3개, 선택과목 2개를 포함하지 않습니다. 과목을 추가해 주세요!");
                // 나중에 수강생 삭제 기능 구현후, 수강생 등록 포기 기능 추가

                System.out.println("============== 과목조건 미달로인한 추가신청 ==============");
                System.out.println("수강가능 과목들(필수 최소 3개, 선택 최소 2개): "); // 나중에 수정
                for (Subject s : CampManagementApplication.getSubjectStore()) {
                    if (s.getSubjectType().equals("MANDATORY"))
                        System.out.println(s.getSubjectId().charAt(2) + ". " + s.getSubjectName() + " (필수)");
                    else
                        System.out.println(s.getSubjectId().charAt(2) + ". " + s.getSubjectName() + " (선택)");
                }
            }
        }


        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 상태입력 메서드 추가
    public String displayStudentCondition() {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("수강생 상태 입력 :");
            System.out.println("1. 양호");
            System.out.println("2. 주의");
            System.out.println("3. 위험");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

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
        return null;
    }
}
