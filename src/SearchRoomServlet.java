import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by percy on 8/31/16..
 */
@WebServlet("/SearchRoomServlet")
public class SearchRoomServlet extends HttpServlet {

    private String username = "test";
    private String password = "test";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean loggedIn = false;
        if (request.getParameter("username").equals("test") && request.getParameter("password").equals("test")) {
            loggedIn = true;
        }

        if (loggedIn) {
            response.getWriter().println("Login succesfull");
        } else {
            response.sendRedirect("fouteinlog.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private boolean isValidLogin(String username, String password) {
        ArrayList<User> users = Storage.getInstance().getUsers();
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                if (user.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
}
