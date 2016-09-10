package nl.kamerverhuur;

import nl.kamerverhuur.users.User;
import nl.kamerverhuur.users.UserType;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Ruben on 9/9/2016.
 */
public class KamerverhuurUtils {

    /**
     * Method to check the type of user
     * @param username the String of the username field
     * @return the type of the user
     */
    public static UserType getUserType(String username) {
        for (User user : Storage.getInstance().getUsers()) {
            if (user.getUserName().equals(username)) {
                return user.getType();
            }
        }
        return null; // impossible
    }

    /**
     * Get the username from the cookie
     * @param request the request
     * @return the string of the username
     */
    public static String getUserNameFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if(cookies != null){
            for (Cookie cookie:cookies) {
                if (cookie.getName().equals("loggedInUser")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * Delete all cookies
     * @param request the request
     * @param response the response
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();

        if(cookies != null){
            for (Cookie cookie:cookies) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }
}
