import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;

public class Sss extends HttpServlet {
    public Sss() {
        super();
        System.out.println("Sss:constructor");
    }
    public void init(ServletConfig sc) throws ServletException {
        super.init();
        System.out.println("Sss:init");
    }
    public void destroy() {
        super.destroy();
        System.out.println("Sss:destroy");
    }

    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("Sss:service: " + req.getMethod());
        res.getWriter().println("Sss:service" + req.getMethod());
        res.getWriter().println("Server name: " + req.getServerName());
        res.getWriter().println("Host ip: " + InetAddress.getLocalHost());

        String method = req.getMethod();
        if ("GET".equals(method)) {
            this.doGet(req, res);
        } else if ("POST".equals(method)) {
            this.doPost(req, res);
        }

    }

    public   void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("doGet");

        response.getWriter().println(request.getRequestURL());
        response.getWriter().println(request.getParameter("firstname") + " " +  request.getParameter("lastname"));
    }

    public void  doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("doPost");

        response.getWriter().println(request.getParameter("firstname") + " " +  request.getParameter("lastname"));
    }

}
