import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;

        // Nhập số lượng sinh viên
        System.out.println("Nhập vào số lượng sinh viên:");
        n = scanner.nextInt();

        // Nhập thông tin sinh viên
        for (int i = 1; i <= n; i++) {
            input();
        }

        // In danh sách sinh viên
        System.out.println("Danh sách sinh viên:");
        output();

        // Thêm các chức năng khác cho người dùng lựa chọn
        boolean exit = false;
        while (!exit) {
            System.out.println("\nChọn một chức năng:");
            System.out.println("1. Xóa sinh viên theo mã");
            System.out.println("2. Sắp xếp sinh viên theo điểm giảm dần");
            System.out.println("3. Tìm sinh viên theo mã hoặc tên");
            System.out.println("4. Lọc sinh viên theo điểm");
            System.out.println("5. In danh sách sinh viên");
            System.out.println("0. Thoát");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Đọc bỏ dòng mới sau khi nhập số

            switch (choice) {
                case 1:
                    System.out.println("Nhập mã sinh viên cần xóa:");
                    String codeToRemove = scanner.nextLine();
                    removeByCode(codeToRemove);
                    System.out.println("Đã xóa sinh viên có mã " + codeToRemove);
                    break;
                case 2:
                    sortByGradeDesc();
                    System.out.println("Đã sắp xếp sinh viên theo điểm giảm dần.");
                    break;
                case 3:
                    System.out.println("Nhập mã hoặc tên sinh viên cần tìm:");
                    String keyword = scanner.nextLine();
                    Student foundStudent = findByCodeOrName(keyword);
                    if (foundStudent != null) {
                        System.out.println("Sinh viên tìm thấy: " + foundStudent);
                    } else {
                        System.out.println("Không tìm thấy sinh viên nào.");
                    }
                    break;
                case 4:
                    System.out.println("Nhập điểm để lọc danh sách sinh viên:");
                    float gradeThreshold = scanner.nextFloat();
                    List<Student> filteredStudents = filterByGrade(gradeThreshold);
                    System.out.println("Danh sách sinh viên có điểm >= " + gradeThreshold + ":");
                    for (Student student : filteredStudents) {
                        System.out.println(student);
                    }
                    break;
                case 5:
                    output();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại.");
                    break;
            }
        }
        scanner.close();
    }

    // Nhập thông tin cho mỗi sinh viên
    public static void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập vào thông tin sinh viên:");

        System.out.print("Nhập mã sinh viên: ");
        String code = scanner.nextLine();

        System.out.print("Nhập tên sinh viên: ");
        String name = scanner.nextLine();

        System.out.print("Nhập điểm: ");
        float grade = scanner.nextFloat();

        Student student = new Student(code, name, grade);
        studentList.add(student);
    }

    // In danh sách sinh viên
    public static void output() {
        for (Student student : studentList) {
            System.out.println(student.toString());
        }
    }

    // Xóa sinh viên theo mã
    public static void removeByCode(String code) {
        studentList.removeIf(student -> student.getCode().equals(code));
    }

    // Sắp xếp danh sách sinh viên theo điểm giảm dần
    public static void sortByGradeDesc() {
        studentList.sort((s1, s2) -> Float.compare(s2.getGrade(), s1.getGrade()));
    }

    // Tìm sinh viên theo mã hoặc tên
    public static Student findByCodeOrName(String keyword) {
        for (Student student : studentList) {
            if (student.getCode().equals(keyword) || student.getName().equalsIgnoreCase(keyword)) {
                return student;
            }
        }
        return null;
    }

    // Lọc danh sách sinh viên theo điểm
    public static List<Student> filterByGrade(float x) {
        List<Student> filteredList = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getGrade() >= x) {
                filteredList.add(student);
            }
        }
        return filteredList;
    }
}
