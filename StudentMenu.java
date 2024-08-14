import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class StudentMenu {
    private static final List<String> courses = new ArrayList<>();
    private static final String COURSES_FILE = "courses.txt";

    public static void main(String[] args) {
        loadExistingCourses();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String select = "";
            do {
                showMenu();
                select = br.readLine();
                switch (select) {
                    case "1":
                        addStudent(br);
                        break;
                    case "2":
                        searchStudents(br);
                        break;
                    case "3":
                        generateReport();
                        break;
                    case "4":
                        addCourse(br);
                        break;
                    case "5":
                        viewAllStudents();
                        break;
                    case "6":
                        return;
                }
            } while (!select.equals("6"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showMenu() {
        System.out.println("1: Add student");
        System.out.println("2: Search students");
        System.out.println("3: Generate report");
        System.out.println("4: Add course");
        System.out.println("5: View all students");
        System.out.println("6: Exit");
        System.out.println("Select Number (1-6):");
    }

    private static void addStudent(BufferedReader br) throws IOException {
        System.out.println("Enter student number:");
        String studentNo = br.readLine();
        System.out.println("Enter student name:");
        String studentName = br.readLine();
        System.out.println("Enter student email:");
        String email = br.readLine();
        System.out.println("Enter student phone:");
        String phone = br.readLine();
        System.out.println("Enter student course:");
        String course = br.readLine();
        Student student = new Student(studentNo, studentName, email, phone, course);
        if (Student.saveStudent(student)) {
            System.out.println("Student saved successfully!");
        } else {
            System.out.println("Error occurred while saving student information.");
        }
    }

    private static void searchStudents(BufferedReader br) throws IOException {
        System.out.println("Enter search term:");
        String searchTerm = br.readLine();
        List<Student> matchingStudents = Student.searchStudents(searchTerm);
        if (matchingStudents.isEmpty()) {
            System.out.println("No matching students found.");
        } else {
            System.out.println("Matching students:");
            for (Student student : matchingStudents) {
                System.out.println(student);
            }
        }
    }

    private static void generateReport() {
        List<Student> allStudents = Student.getAllStudents();
        System.out.println("Course Enrollment Report:");
        for (String course : courses) {
            long count = allStudents.stream().filter(s -> s.course.equals(course)).count();
            System.out.println(course + ": " + count + " students");
        }
    }

    private static void addCourse(BufferedReader br) throws IOException {
        System.out.println("Enter course name:");
        String courseName = br.readLine();
        if (!courseName.isEmpty()) {
            if (courses.contains(courseName)) {
                System.out.println("Course already exists.");
            } else {
                courses.add(courseName);
                saveCourses();
                System.out.println("Course added successfully!");
            }
        } else {
            System.out.println("Course name cannot be empty.");
        }
    }

    private static void viewAllStudents() {
        List<Student> allStudents = Student.getAllStudents();
        if (allStudents.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("All Students:");
            for (Student student : allStudents) {
                System.out.println(student);
            }
        }
    }

    private static void loadExistingCourses() {
        try (BufferedReader br = new BufferedReader(new FileReader(COURSES_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                courses.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error loading courses: " + e.getMessage());
        }
    }

    private static void saveCourses() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(COURSES_FILE))) {
            for (String course : courses) {
                pw.println(course);
            }
        } catch (IOException e) {
            System.out.println("Error saving courses: " + e.getMessage());
        }
    }
}
