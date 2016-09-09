package nl.kamerverhuur.servlets;

import nl.kamerverhuur.KamerverhuurUtils;
import nl.kamerverhuur.Residence;
import nl.kamerverhuur.Storage;

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
     * Initialise the servlet. The only thing we have to init is the residences array.
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        residences = Storage.getInstance().getResidences();
    }

    /**
     * The post method. Here we either add a new room or redirect to the page to add a new room
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
     * The get method. Here we show a list of rooms owned by the Verhuurder
     * @param request the request
     * @param response the response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">");
        out.println("<html><head><title>Show Rooms</title></head><body>");

        for (Residence residence : residences){
            if (residence.getName().equals(KamerverhuurUtils.getUserNameFromCookie(request))){
                out.println(residence.toHTML());
            }
        }

        out.println("<form action=\"/ShowRoomsServlet\" method=\"post\">" +
                "<input type=\"hidden\" name=\"addroom\" value=\"true\">" +
                "<input type=\"submit\" name=\"submit\">" +
                "</form>");
        out.println("</body></html>");
    }
}
