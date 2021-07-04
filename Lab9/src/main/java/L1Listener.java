import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener()
public class L1Listener implements ServletContextListener{

    // Public constructor is required by servlet spec
    public L1Listener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context was initialized");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context was destroyed");
    }
}
