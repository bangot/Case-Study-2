import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String choose = null;
        boolean exit = false;
        StudentManager studentManager = new StudentManager();
        int studentId;

        showMenu();
        while (true) {
            choose = scanner.nextLine();
            switch (choose) {
                case "1":
                    studentManager.add();
                    break;
                case "2":
                    studentId = studentManager.inputId();
                    studentManager.edit(studentId);
                    break;
                case "3":
                    studentId = studentManager.inputId();
                    studentManager.delete(studentId);
                    break;
                case "4":
                    studentManager.sortStudentByGPA();
                    break;
                case "5":
                    studentManager.sortStudentByName();
                    break;
                case "6":
                    studentManager.show();
                    break;
                case "0":
                    System.out.println("exited!");
                    exit = true;
                    break;
                default:
                    System.out.println("ban da nhap sai gioi han chuc nang");
                    break;
            }
            if (exit) {
                break;
            }
            showMenu();
        }
    }

    public static void showMenu() {
        System.out.println("-----------menu------------");
        System.out.println("1. Them sinh vien");
        System.out.println("2. Sua thông tin sinh vien theo id");
        System.out.println("3. Xoa thong tin sinh vien theo id.");
        System.out.println("4. Xap xep theo diem trung binh.");
        System.out.println("5. Xap xep theo ten.");
        System.out.println("6. Hien thi.");
        System.out.println("0. Thoat.");
        System.out.println("---------------------------");
        System.out.print("Vui long tron: ");
    }
}