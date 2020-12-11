import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentManager {
    public static Scanner scanner = new Scanner(System.in);
    private ArrayList<Student> students = new ArrayList<>();
    private StudentIo studentIo;

    public StudentManager() {
        studentIo = new StudentIo();

        students = (ArrayList<Student>) studentIo.read();
    }

    public void add() {
        int id = (students.size() > 0) ? (students.size() + 1) : 1;
        System.out.println("student id = " + id);
        String name = inputName();
        byte age = inputAge();
        String address = inputAddress();
        float gpa = inputGpa();

        Student student = new Student(id, name, age, address, gpa);
        students.add(student);
        studentIo.write(students);
    }


    public void edit(int id) {
        boolean isExisted = false;
        int size = students.size();
        for (int i = 0; i < size; i++) {
            if (students.get(i).getId() == id) {
                isExisted = true;
                students.get(i).setName(inputName());
                students.get(i).setAge(inputAge());
                students.get(i).setAddress(inputAddress());
                students.get(i).setGpa(inputGpa());
                break;
            }
        }
        if (!isExisted) {
            System.out.printf("id = %d not existed.\n", id);
        } else {
            studentIo.write(students);
        }
    }


    public void delete(int id) {
        Student student = null;
        int size = students.size();
        for (int i = 0; i < size; i++) {
            if (students.get(i).getId() == id) {
                student = students.get(i);
                break;
            }
        }
        if (student != null) {
            students.remove(student);
            studentIo.write(students);
        } else {
            System.out.printf("id = %d khong ton tai.\n", id);
        }
    }

    public void sortStudentByName() {
        Collections.sort(students, new SortStudentByName());
    }


    public void sortStudentByGPA() {
        Collections.sort(students, new SortStudentMediumGPA());
    }

    public void show() {
        for (Student student : students) {
            System.out.format("%5d | ", student.getId());
            System.out.format("%20s | ", student.getName());
            System.out.format("%5d | ", student.getAge());
            System.out.format("%20s | ", student.getAddress());
            System.out.format("%10.1f%n", student.getGpa());

        }
    }


    public int inputId() {
        System.out.print("Nhap id sinh vien ");
        while (true) {
            try {
                int id = Integer.parseInt((scanner.nextLine()));
                return id;
            } catch (NumberFormatException ex) {
                System.out.print(" id khong hop le ");
            }
        }
    }

    private String inputName() {
        System.out.print("Nhap ho ten: ");
        return scanner.nextLine();
    }


    private String inputAddress() {
        System.out.print("Nhap gioi tinh: ");
        return scanner.nextLine();
    }

    private byte inputAge() {
        System.out.print("Nhap tuoi: ");
        while (true) {
            try {
                byte age = Byte.parseByte((scanner.nextLine()));
                if (age < 0 || age > 100) {
                    throw new NumberFormatException();
                }
                return age;
            } catch (NumberFormatException ex) {
                System.out.print("Tuoi khong hop le, nhap lai ");
            }
        }
    }


    private float inputGpa() {
        System.out.print("Nhap diem trung binh: ");
        while (true) {
            try {
                float gpa = Float.parseFloat((scanner.nextLine()));
                if (gpa < 0.0 || gpa > 10.0) {
                    throw new NumberFormatException();
                }
                return gpa;
            } catch (NumberFormatException ex) {
                System.out.print("diem ban nhap khong hop le ,nhap lai: ");
            }
        }
    }


    public ArrayList<Student> getStudents() {

        return students;
    }

    public void setStudents(ArrayList<Student> student) {
        this.students = (ArrayList<Student>) students;
    }
}
