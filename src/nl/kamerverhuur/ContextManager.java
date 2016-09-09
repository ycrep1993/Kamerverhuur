package nl.kamerverhuur;

import nl.kamerverhuur.users.UserType;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Ruben on 9/9/2016.
 */
public class ContextManager implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Storage storage = Storage.getInstance();

        storage.addUser("beheerder", "admin123", UserType.BEHEERDER);
        storage.addUser("huurder", "huurder123", UserType.HUURDER);
        storage.addUser("verhuurder", "verhuurder123", UserType.VERHUURDER);
        storage.addUser("verhuurder2", "verhuurder2123", UserType.VERHUURDER);

        storage.addRoom(10, 100, "Almelo", "verhuurder", 1);
        storage.addRoom(20, 200, "Hengelo", "verhuurder", 1);
        storage.addRoom(30, 300, "Enschede", "verhuurder2", 1);
        storage.addRoom(20, 350, "Enschede", "verhuurder2", 1);
        storage.addRoom(60, 250, "Enschede", "henkie", 1);
        storage.addRoom(11, 320, "Enschede", "pietje", 3);
        storage.addRoom(30, 3009, "Enschede", "verhuurder2", 1);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
