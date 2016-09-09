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

    /**
     * Init the servlet. Only we dont have any initial values for this one
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        //We dont have anything to init here

        super.init();
    }

    /**
     * The post method, only we dont post to this servlet
     * @param request the request
     * @param response the response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //We dont post to this servlet
    }

    /**
     * Here we show a list of persons
     * @param request the request
     * @param response the response
     * @throws ServletException
     * @throws IOException
     */
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
