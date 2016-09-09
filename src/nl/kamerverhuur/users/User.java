package nl.kamerverhuur.users;

/**
 * Created by Percy on 7-9-2016.
 */
public class User {

    private String userName;
    private String password;
    private UserType type;

    /**
     * Constructor for nl.kamerverhuur.users.User class
     * @param userName name for the user
     * @param password password for the user
     * @param type the type of the user
     */
    public User(String userName, String password, UserType type) {
        this.userName = userName;
        this.password = password;
        this.type = type;
    }

    /**
     * returns userName
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * returns password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * returns nl.kamerverhuur.users.UserType
     * @return nl.kamerverhuur.users.UserType
     */
    public UserType getType() {
        return type;
    }
}
