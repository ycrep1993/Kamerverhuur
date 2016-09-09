import java.util.ArrayList;

/**
 * Created by Ruben on 9/9/2016.
 */
public class Storage {

    private ArrayList<User> users = new ArrayList<>();
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
}
