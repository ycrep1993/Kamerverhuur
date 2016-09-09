package nl.kamerverhuur;

import nl.kamerverhuur.users.User;
import nl.kamerverhuur.users.UserType;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Ruben on 9/9/2016.
 */
public class Storage {

    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Residence> residences = new ArrayList<>();

    private static Storage instance;

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(String userName, String password, UserType type) {
        users.add(new User(userName, password, type));
    }

    public ArrayList<Residence> getResidences() {
        return residences;
    }

    public void addRoom(double vierkanteMeters, double huurprijs, String plaats, String naam, int slaapkamers){
        residences.add(new Residence(vierkanteMeters, huurprijs, plaats, naam, slaapkamers));
    }

    public void saveLoggedInUserInCookie(HttpServletResponse response, String userName) throws IOException{

        Cookie loggedInUserCookie = new Cookie("loggedInUser", userName);
        loggedInUserCookie.setMaxAge(60*60);
        response.addCookie(loggedInUserCookie);
    }
}
