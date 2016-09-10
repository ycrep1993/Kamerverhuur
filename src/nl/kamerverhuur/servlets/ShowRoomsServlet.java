package nl.kamerverhuur.servlets;

import nl.kamerverhuur.KamerverhuurUtils;
import nl.kamerverhuur.Residence;
import nl.kamerverhuur.Storage;
import nl.kamerverhuur.users.UserType;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by percy on 8/31/16..
 */
@WebServlet("/ShowRoomsServlet")
public class ShowRoomsServlet extends HttpServlet {

    private ArrayList<Residence> residences;

    /**
     * Initialise the arraylist with residences
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        residences = Storage.getInstance().getResidences();
    }

    /**
     * Forward to the addroom html or add a new room
     * @param request the request
     * @param response the response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("addroom") != null && request.getParameter("addroom").equals("true")){
            getServletContext().getRequestDispatcher("/WEB-INF/addroom.html").forward(request, response);
        }else{
            Storage.getInstance().addRoom(Double.parseDouble(request.getParameter("vierkantemeters")),
                    Double.parseDouble(request.getParameter("huurprijs")),
                    request.getParameter("plaats"),
                    request.getParameter("naam"),
                    Integer.parseInt(request.getParameter("slaapkamers")));

            doGet(request, response);
        }
    }

    /**
     * Show a list of rooms
     * @param request the request
     * @param response the response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (KamerverhuurUtils.getUserType(KamerverhuurUtils.getUserNameFromCookie(request)) == UserType.BEHEERDER) {
            PrintWriter out = response.getWriter();
            out.println("<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">");
            out.println("<html><head><title>Show Rooms</title></head><body>");

            for (Residence residence : residences) {
                if (residence.getName().equals(KamerverhuurUtils.getUserNameFromCookie(request))) {
                    out.println(residence.toHTML());
                }
            }

            out.println("<form action=\"/ShowRoomsServlet\" method=\"post\">" +
                    "<input type=\"hidden\" name=\"addroom\" value=\"true\">" +
                    "<input type=\"submit\" name=\"submit\">" +
                    "</form>");

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
}
