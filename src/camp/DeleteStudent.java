package camp;

import camp.model.Student;

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
