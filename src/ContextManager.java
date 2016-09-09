import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Ruben on 9/9/2016.
 */
public class ContextManager implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Storage.getInstance().addUser("beheerder", "admin123", UserType.BEHEERDER);
        Storage.getInstance().addUser("huurder", "huurder123", UserType.HUURDER);
        Storage.getInstance().addUser("verhuurder", "verhuurder123", UserType.VERHUURDER);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
