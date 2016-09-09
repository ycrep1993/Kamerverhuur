package nl.kamerverhuur.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by percy on 8/31/16..
 */
@WebServlet("/nl.kamerverhuur.ShowPersonsServlet")
public class ShowPersonsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("Logged in as beheerder1");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Cookie loginCookie = null;

        if(cookies != null){
            for (Cookie cookie:cookies) {
                if (cookie.getName().equals("loggedInUser")){
                    response.getWriter().println(cookie.getValue());
                }
            }
        }
    }
}
