package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;
import java.util.*;


/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */
public class CampManagementApplication {
    // 데이터 저장소
    private static Map<String, Student> studentStore; // 어떤 학생들이 있는지 담은 리스트
    public static List<Subject> subjectStore; // 무슨 과목들이 있는지 담은 리스트
    private static List<Score> scoreStore; // 어떤 점수들이 있는지 다은 리스트
    public static Map<String, String> studentMap = new HashMap<>(); //<ST1, 학생이름>을 <key, Value>로 담은 딕셔너리


    // for push sungju
    // 과목 타입
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "CHOICE";


    // index 관리 필드
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";


    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    //전역 변수 저장소
    static String studentName;


    public static void main(String[] args) {
        setInitData();
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }


    // 초기 데이터 생성
    private static void setInitData() {
        studentStore = new HashMap<>();
        subjectStore = List.of(
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Java",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "객체지향",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "JPA",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MySQL",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "디자인 패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring Security",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Redis",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MongoDB",
                        SUBJECT_TYPE_CHOICE
                )
        );
        scoreStore = new ArrayList<>();
    }


    // index 자동 증가
    private static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                studentMap.put(INDEX_TYPE_STUDENT + studentIndex, studentName);
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
            }
        }
    }


    private static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();


            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }


    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 수강생 정보 수정");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();


            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> updateStudent(); // 수강생 정보 수정
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }


    // 수강생 등록
    private static void createStudent() {
        System.out.println("\n<수강생을 등록>");
        System.out.print("수강생 이름 입력: ");
        studentName = sc.next();

        // 수강생 상태 등록 리펙토링
        String studentCondition = displayStudentCondition();

        // 수강생 상태 기입란 추가
        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName, studentCondition); // 수강생 인스턴스 생성 예시 코드
        // 기능 구현
        studentStore.put(student.getStudentId(), student.getStudent());


        System.out.println("수강가능 과목: "); // 나중에 수정
        for (Subject s : subjectStore) {
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

    // 수강생 상태입력 메서드 추가
    private static String displayStudentCondition() {
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


    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");

//        String studentId = sc.next();
//
//        Student student = studentStore.get(studentId);

        // 기능 구현
        for (Student student : studentStore.values()) {
            System.out.println(student.getStudentId() + " " + student.getStudentName());
        }
        System.out.println("\n수강생 목록 조회 성공!");

        // 정보조회 기능 구현
        System.out.println("수강생 정보를 조회하시겠습니까? (y / n)");
        String inputInfo = sc.next();
        if (inputInfo.equals("y")){
            System.out.print("ST넘버를 선택하세요... 예)'ST1', 'ST2'...");
            String inputST = sc.next();
            System.out.println(studentMap.containsKey(inputST));
            if (studentMap.containsKey(inputST)){
                System.out.println(inputST+"의 수강생 정보는 다음과 같습니다");
                System.out.println(inputST+" "+studentMap.get(inputST)+" "+displayStudentCondition()+" "+"선택과목명");


            }
            else{
                System.out.print("존재하지 않는 ST넘버입니다. 수강생 목록에 있는 ST넘버를 입력해주세요.");
            }
        }





    }


    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();


            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }


    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }


    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        System.out.println("시험 점수를 등록합니다...");
        // 기능 구현

        for (Student student : studentStore.values()) {
            if (student.getStudentId().equals(studentId)) {
                student.setScore();
                break;
            }
        }

        System.out.println("\n점수 등록 성공!");
    }


    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("시험 점수를 수정합니다...");
        System.out.println("수정할 과목을 입력하세요...");
        String subjectName = sc.next();

        for (Student student : studentStore.values()) {
            int cnt1 = 0;
            if (student.getStudentId().equals(studentId)) {
                for (Map.Entry<Subject, List<Score>> entry : student.getSubjectScores().entrySet()) {
                    int cnt2 = 0;
                    Subject subject = entry.getKey();
                    if (subject.getSubjectName().equals(subjectName)) {
                        cnt1++;
                        System.out.println("Subject: " + subject.getSubjectName());
                        List<Score> scores = entry.getValue();
                        System.out.println("점수를 수정할 회차를 입력하세요...");
                        int round = sc.nextInt();
                        for (Score score : scores) {
                            if (score.getRound() == round) {
                                cnt2++;
                                System.out.println("새로운 점수를 입력하세요...");
                                int scoreToEdit = sc.nextInt();
                                System.out.println("수정하기 전 점수");
                                System.out.println("Round: " + score.getRound() + " Score: " + score.getScore());
                                score.setScore(scoreToEdit);
                                System.out.println("수정한 점수");
                                System.out.println("Round: " + score.getRound() + " Score: " + score.getScore());
                            }
                        }
                        if (cnt2 == 0) {
                            System.out.println("등록되지 않은 회차입니다...");
                        }
                        break;
                    }
                }
                if (cnt1 == 0) {
                    System.out.println("수강생이 등록한 과목이 아닙니다...");
                }
                break;
            }
        }
        System.out.println("\n점수 수정 성공!");
    }


    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호

        System.out.println("조회할 과목을 입력하세요...");
        String subjectName = sc.next();

        for (Student student : studentStore.values()) {
            int cnt = 0;
            if (student.getStudentId().equals(studentId)) {
                for (Map.Entry<Subject, List<Score>> entry : student.getSubjectScores().entrySet()) {
                    Subject subject = entry.getKey();
                    if (subject.getSubjectName().equals(subjectName)) {
                        cnt++;
                        System.out.println("Subject: " + subject.getSubjectName());
                        List<Score> scores = entry.getValue();
                        for (Score score : scores) {
                            System.out.println("Round: " + score.getRound() + " Grade: " + score.getGrade());
                        }
                        break;
                    }
                }
                if (cnt == 0) {
                    System.out.println("수강생이 등록한 과목이 아닙니다...");
                }
                break;
            }
        }
        System.out.println("\n등급 조회 성공!");
    }

    private static void updateStudent() {
        if (!studentStore.isEmpty()) {
            boolean flag = true;
            while (flag) {
                System.out.println("\n수정할 수강생의 ID를 입력하세요 :");
                String studentId = sc.next();
                Student student = studentStore.get(studentId);

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
                    student.setStudentCondition(displayStudentCondition());
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