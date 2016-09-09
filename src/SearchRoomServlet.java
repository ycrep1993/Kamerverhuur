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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean loggedIn = false;
        Storage storage = Storage.getInstance();
        if (isValidLogin(request.getParameter("username"), request.getParameter("password"))) {
            UserType type = getUserType(request.getParameter("username"));
            if (type == UserType.BEHEERDER) {
                response.sendRedirect("/ShowPersonsServlet");
            } else if (type == UserType.VERHUURDER) {
                getServletContext().getRequestDispatcher("/WEB-INF/addroom.html").forward(request, response);
            } else if (type == UserType.HUURDER) {
                response.getWriter().println("Logged in as huurder");
            }
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

    private UserType getUserType(String username) {
        ArrayList<User> users = Storage.getInstance().getUsers();
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return user.getType();
            }
        }
        return null; // impossible
    }
}
