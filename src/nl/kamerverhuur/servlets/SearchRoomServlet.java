package nl.kamerverhuur.servlets;

import nl.kamerverhuur.*;
import nl.kamerverhuur.users.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * In this class the user might get redirected to other nl.kamerverhuur.servlets and htmls depending on nl.kamerverhuur.users.UserType.
 * If the user does not get redirected (user statys in this class), the user will be able to search available rooms.
 */
@WebServlet("/SearchRoomServlet")
public class SearchRoomServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (isValidLogin(request.getParameter("username"), request.getParameter("password"))) {
            UserType type = getUserType(request.getParameter("username"));
            makeCookie(response, request.getParameter("username"));
            if (type == UserType.BEHEERDER) {
                response.sendRedirect("/nl.kamerverhuur.ShowPersonsServlet");
            } else if (type == UserType.VERHUURDER) {
                getServletContext().getRequestDispatcher("/WEB-INF/addroom.html").forward(request, response);
            } else if (type == UserType.HUURDER) {
                getServletContext().getRequestDispatcher("/WEB-INF/huurder.html").forward(request, response);
            }
        } else {
            response.sendRedirect("fouteinlog.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Method to check whether a login is valid
     * @param username the String of the username field
     * @param password the String of the password field
     * @return true if user exists, false if not
     */
    private boolean isValidLogin(String username, String password) {
        for (User user : Storage.getInstance().getUsers()) {
            if (user.getUserName().equals(username)) {
                if (user.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method to check the type of user
     * @param username the String of the username field
     * @return the type of the user
     */
    private UserType getUserType(String username) {
        for (User user : Storage.getInstance().getUsers()) {
            if (user.getUserName().equals(username)) {
                return user.getType();
            }
        }
        return null; // impossible
    }

    private void makeCookie(HttpServletResponse response, String userName ){
        Cookie loggedInUserCookie = new Cookie("loggedInUser", userName);
        loggedInUserCookie.setMaxAge(60*60);
        response.addCookie(loggedInUserCookie);
    }
}
