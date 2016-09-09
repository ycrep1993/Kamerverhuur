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
import java.io.PrintWriter;
import java.util.ArrayList;


@WebServlet("/SearchRoomServlet")
public class SearchRoomServlet extends HttpServlet {

    private ArrayList<Residence> residences;

    @Override
    public void init() throws ServletException {
        residences = Storage.getInstance().getResidences();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double minSquareMeters = Double.parseDouble(request.getParameter("min_vierkante_meters"));
        double maxSquareMeters = Double.parseDouble(request.getParameter("max_vierkante_meters"));
        double minRent = Double.parseDouble(request.getParameter("min_huurprijs"));
        double maxRent = Double.parseDouble(request.getParameter("max_huurprijs"));
        int minBedrooms = Integer.parseInt(request.getParameter("slaapkamers"));
        String plaats = request.getParameter("plaats");

        PrintWriter out = response.getWriter();
        out.println("<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>");
        out.println("Kamers zoeken");
        out.println("</title>");
        out.println("<body>");

        for (Residence residence:residences) {
            if (residence.getSquareMeters() >= minSquareMeters &&
                    residence.getSquareMeters() <= maxSquareMeters &&
                    residence.getRent() >= minRent &&
                    residence.getRent() <= maxRent &&
                    residence.getBedrooms() >= minBedrooms &&
                    residence.getCity().equals(plaats)){

                out.println(residence.toHTML());
            }
        }

        out.println("</body>");
        out.println("</head>");
        out.println("</hmtl>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //We dont have to do anything here
    }

}
