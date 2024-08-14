import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AddStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentNo = request.getParameter("studentNo");
        String studentName = request.getParameter("studentName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String course = request.getParameter("course");

        Student student = new Student(studentNo, studentName, email, phone, course);
        if (Student.saveStudent(student)) {
            response.getWriter().write("Student saved successfully!");
        } else {
            response.getWriter().write("Error occurred while saving student information.");
        }
    }
}
