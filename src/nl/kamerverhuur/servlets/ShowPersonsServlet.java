package nl.kamerverhuur.servlets;

import nl.kamerverhuur.KamerverhuurUtils;
import nl.kamerverhuur.Storage;
import nl.kamerverhuur.users.User;
import nl.kamerverhuur.users.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by percy on 8/31/16..
 */
@WebServlet("/ShowPersonsServlet")
public class ShowPersonsServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        //We dont have anything to init here

        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //We never get here using post
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cookieCounter(request, response);
        if (KamerverhuurUtils.getUserType(KamerverhuurUtils.getUserNameFromCookie(request)) == UserType.BEHEERDER) {
            PrintWriter out = response.getWriter();
            String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";
            String title = "Show persons";

            out.println(docType + "<html><head><title>" + title + "</title></head><body>");

            Cookie[] cookies = request.getCookies();

            for (Cookie cookie : cookies){
                if (cookie.getName().equals("counterCookie")){
                    out.println("Aantal keren bezocht: " + cookie.getValue() + "</br></br>");
                }

                if (cookie.getName().equals("dateCookie")){
                    out.println("Laatst bezocht op: " + cookie.getValue() + "</br></br>");
                }
            }

            for (User user : Storage.getInstance().getUsers()) {
                out.println(
                        "Name: "+user.getUserName()+"<br/>\n" +
                        "Password: "+user.getPassword()+"<br/>\n" +
                        "Type: "+user.getType()+"<br/><br/>\n");
            }

            out.println("<form action=\"/LoginServlet\" method=\"post\">" +
                    "<input type=\"hidden\" name=\"extra\" value=\"logout\">" +
                    "<input type=\"submit\" name=\"submit\" value=\"logout\">" +
                    "</form>");

            out.println("</body></html>");
        } else {
            response.getWriter().println("access NOT allowed");
        }
        destroy();
    }

    private void cookieCounter(HttpServletRequest request, HttpServletResponse response){
        int currentCount = 0;

        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies){
            if (cookie.getName().equals("counterCookie")){
                currentCount = Integer.parseInt(cookie.getValue()) + 1;
            }
        }

        Cookie counterCookie = new Cookie("counterCookie", String.valueOf(currentCount));

        counterCookie.setMaxAge(2000000000);
        response.addCookie(counterCookie);

        Date currentDate = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateString = df.format(currentDate);

        Cookie dateCookie = new Cookie("dateCookie", currentDateString);
        dateCookie.setMaxAge(2000000000);
        response.addCookie(dateCookie);
    }
}
