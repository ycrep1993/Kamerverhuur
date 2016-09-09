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
        response.getWriter().println("Logged in as beheerder1");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (KamerverhuurUtils.getUserType(KamerverhuurUtils.getUserNameFromCookie(request)) == UserType.BEHEERDER) {
            PrintWriter out = response.getWriter();
            String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";
            String title = "Show persons";

            for (User user : Storage.getInstance().getUsers()) {
                out.println(docType +
                        "<html>\n" +
                        "<head><title>" + title + "</title></head>\n" +
                        "Name: "+user.getUserName()+"<br/>\n" +
                        "Password: "+user.getPassword()+"<br/>\n" +
                        "Type: "+user.getType()+"<br/><br/>\n" +
                        "</html>");
            }
        } else {
            response.getWriter().println("access NOT allowed");
        }
    }
}
