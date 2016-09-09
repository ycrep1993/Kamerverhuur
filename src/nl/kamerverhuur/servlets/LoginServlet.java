package nl.kamerverhuur.servlets;

import nl.kamerverhuur.KamerverhuurUtils;
import nl.kamerverhuur.Storage;
import nl.kamerverhuur.users.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * In this class the user might get redirected to other servlets and htmls depending on UserType.
 * Also creates a cookie for the logged in user.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("extra").equals("login")) {
            if (isValidLogin(request.getParameter("username"), request.getParameter("password"))) {
                UserType type = KamerverhuurUtils.getUserType(request.getParameter("username"));
                makeCookie(response, request.getParameter("username"));
                if (type == UserType.BEHEERDER) {
                    response.sendRedirect("/ShowPersonsServlet");
                    destroy();
                } else if (type == UserType.VERHUURDER) {
                    getServletContext().getRequestDispatcher("/WEB-INF/addroom.html").forward(request, response);
                    destroy();
                } else if (type == UserType.HUURDER) {
                    getServletContext().getRequestDispatcher("/WEB-INF/huurder.html").forward(request, response);
                    destroy();
                }
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/foutelogin.html").forward(request, response);
                destroy();
            }
        } else if (request.getParameter("extra").equals("register")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            boolean retry = true;
            String errorText = "";
            if (username.length() > 5) { // username has to be more than 5 characters
                if (isUniqueName(username)) { // username has to be unique
                    if (password.equals(request.getParameter("cpassword"))) { // password much match password confirmation
                        if (password.length() > 5) { // password has to be more than 5 characters
                            UserType type = request.getParameter("typeChoice").equals("huurder")
                                    ? UserType.HUURDER
                                    : UserType.VERHUURDER;
                            Storage.getInstance().addUser(username, password, type);
                            retry = false;
                        } else {
                            errorText = "Password must contain more than 5 characters!";
                        }
                    } else {
                        errorText = "Passwords do not match!";
                    }
                } else {
                    errorText = "Username is already in use!";
                }
            } else {
                errorText = "Username must contain more than 5 characters!";
            }
            PrintWriter out = response.getWriter();
            String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";
            if (retry) {
                String title = "Registration error";
                out.println(docType +
                        "<html>\n" +
                        "<head><title>" + title + "</title></head>\n" +
                        "Registration not successful.<br/>\n" +
                        errorText + "<br/>\n" +
                        "<a href=\"/registreer.html\">Retry.</a>\n" +
                        "</html>");
            } else {
                String title = "Registration sucessful";
                out.println(docType +
                        "<html>\n" +
                        "<head><title>" + title + "</title></head>\n" +
                        "Registration successful!<br/>\n" +
                        "<a href=\"/login.html\">Login.</a>\n" +
                        "</html>");
            }
        } else {
            KamerverhuurUtils.deleteCookie(request);
            response.sendRedirect("login.html");
        }
        destroy();
    }

    @Override
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

    private boolean isUniqueName(String username) {
        for (User user : Storage.getInstance().getUsers()) {
            if (user.getUserName().equals(username)) {
                return false;
            }
        }
        return true;
    }

    private void makeCookie(HttpServletResponse response, String userName ){
        Cookie loggedInUserCookie = new Cookie("loggedInUser", userName);
        loggedInUserCookie.setMaxAge(60*60);
        response.addCookie(loggedInUserCookie);
    }
}
