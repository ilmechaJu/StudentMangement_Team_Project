package camp;

import camp.model.Student;
import camp.model.Subject;

import java.util.Map;
import java.util.Scanner;

public class DeleteStudent {
    public void deleteStudent ()
    {
        Scanner sc = new Scanner(System.in);
        while (true)
        {
            // 숫자가 아닌 입력값 예외처리
            String studentId = CampManagementApplication.getStudentId();

            // find the student
            Map<String, Student> studentStore = CampManagementApplication.getStudentStore();
            // check if the student exists
            if (studentStore.keySet().contains(studentId))
            {
                CampManagementApplication.removeStudentStore(studentId);
                System.out.println("수강생 삭제가 완료되었습니다.");
                return;
            }
            else
            {
                while (true)
                {
                    System.out.println("입력하신 수강생은 존재하지 않습니다. 재입력 하시겠습니까? (1:예, 2:아니오)");
                    String again = sc.next();
                    if (again.equals("1"))
                        break;
                    else if (again.equals("2"))
                        return;
                    else
                        System.out.println("입력오류 입니다. 1 또는 2 만 입력 가능합니다.");
                }
            }
        }
    }
}
//// 수강생 상태 기입란 추가
//Student student = new Student(CampManagementApplication.sequence("ST"), studentName, studentCondition); // 수강생 인스턴스 생성 예시 코드
//// 기능 구현
//// StudentStore 리스트에 student 추가
//        CampManagementApplication.getStudentStore().put(student.getStudentId(), student.getStudent());

//// 수강과목 추가
//// 위 수강신청 루프에서 제대로 수강신청이 되었다면 enrolledStubjects가 비어있지 않으므로 루프가 돌아간다
//            while (!enrolledSubjects.isEmpty())
//        {
//// 입력된 과목번호에서 -1을 하면 subjectStore에서의 같은과목 index번호와 일치한다
//Subject sbj = subjectStore.get(enrolledSubjects.poll()-1);
//// 이렇게 가져온 과목을 학생 객체에 추가!
//                student.setStudentSubject(sbj);
//            }