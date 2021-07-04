import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.io.Console;

@WebListener()
public class L2Listener implements HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public L2Listener() {}

    public void attributeAdded(HttpSessionBindingEvent sbe) {
        System.out.println("Attribute was added");
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        System.out.println("Attribute was removed");
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        System.out.println("Attribute was replaced");
    }
}
