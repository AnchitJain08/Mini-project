import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class SearchStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchTerm = request.getParameter("searchTerm");
        List<Student> matchingStudents = Student.searchStudents(searchTerm);

        if (matchingStudents.isEmpty()) {
            response.getWriter().write("No matching students found.");
        } else {
            for (Student student : matchingStudents) {
                response.getWriter().write(student + "\n");
            }
        }
    }
}
