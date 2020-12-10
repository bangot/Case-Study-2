import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentManager {
    public static Scanner scanner = new Scanner(System.in);
    private List<Student> studentList=new ArrayList<>();
    private StudentIo studentIo;

    public StudentManager() {
        studentIo = new StudentIo();
        studentList = studentIo.read();
    }

    public void add() {
        int id = (studentList.size() > 0) ? (studentList.size() + 1) : 1;
        System.out.println("student id = " + id);
        String name = inputName();
        byte age = inputAge();
        String address = inputAddress();
        float gpa = inputGpa();

        Student student = new Student(id, name, age, address, gpa);
        studentList.add(student);
        studentIo.write(studentList);
    }


    public void edit(int id) {
        boolean isExisted = false;
        int size = studentList.size();
        for (int i = 0; i < size; i++) {
            if (studentList.get(i).getId() == id) {
                isExisted = true;
                studentList.get(i).setName(inputName());
                studentList.get(i).setAge(inputAge());
                studentList.get(i).setAddress(inputAddress());
                studentList.get(i).setGpa(inputGpa());
                break;
            }
        }
        if (!isExisted) {
            System.out.printf("id = %d not existed.\n", id);
        } else {
            studentIo.write(studentList);
        }
    }


    public void delete(int id) {
        Student student = null;
        int size = studentList.size();
        for (int i = 0; i < size; i++) {
            if (studentList.get(i).getId() == id) {
                student = studentList.get(i);
                break;
            }
        }
        if (student != null) {
            studentList.remove(student);
            studentIo.write(studentList);
        } else {
            System.out.printf("id = %d khong ton tai.\n", id);
        }
    }

    public void sortStudentByName() {
        Collections.sort(studentList, new SortStudentByName());
    }


    public void sortStudentByGPA() {
        Collections.sort(studentList, new SortStudentMediumGPA());
    }

    public void show() {
        for (Student student : studentList) {
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


    public List<Student> getStudentList() {

        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
