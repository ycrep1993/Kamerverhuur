/**
 * Created by Percy on 7-9-2016.
 */
public class User {

    private String userName;
    private String password;
    private UserType type;

    public User(String userName, String password, UserType type) {
        this.userName = userName;
        this.password = password;
        this.type = type;
    }
}
