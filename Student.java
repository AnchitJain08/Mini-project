import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private static final String FILE_NAME = "studentdata.txt";
    public String studentNo;
    public String studentName;
    public String email;
    public String phone;
    public String course;

    public Student(String studentNo, String studentName, String email, String phone, String course) {
        this.studentNo = studentNo;
        this.studentName = studentName;
        this.email = email;
        this.phone = phone;
        this.course = course;
    }

    public static boolean saveStudent(Student student) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(new File(FILE_NAME), true))) {
            pw.println(student.studentNo + "," + student.studentName + "," + student.email + "," + student.phone + "," + student.course);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    students.add(new Student(data[0], data[1], data[2], data[3], data[4]));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return students;
    }

    public static List<Student> searchStudents(String searchTerm) {
        List<Student> allStudents = getAllStudents();
        List<Student> matchingStudents = new ArrayList<>();
        for (Student student : allStudents) {
            if (student.toString().toLowerCase().contains(searchTerm.toLowerCase())) {
                matchingStudents.add(student);
            }
        }
        return matchingStudents;
    }

    @Override
    public String toString() {
        return studentNo + "," + studentName + "," + email + "," + phone + "," + course;
    }
}
