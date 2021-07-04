import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Ggg extends HttpServlet {
    public Ggg() {
        super();
        System.out.println("Ggg:constructor");
    }
    public void init(ServletConfig sc) throws ServletException {
        super.init();
        System.out.println("Ggg:init");
    }
    public void destroy() {
        super.destroy();
        System.out.println("Ggg:destroy");
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Called Service in Ggg");
        if(request.getMethod().equals("POST")){
            System.out.println("Called POST in Ggg");
            response.getWriter().println(request.getParameter("name") + " " +  request.getParameter("surname"));
        }
        else if(request.getMethod().equals("GET")){
            System.out.println("Called GET in Ggg");
//              response.getWriter().println(request.getQueryString());
//            RequestDispatcher rd = request.getRequestDispatcher("/page1.html");
//            rd.forward(request, response);

            // response.sendRedirect("/Lab4_war/page1.html");

            response.getWriter().println(request.getParameter("name") + " " +  request.getParameter("surname"));
        }
    }
}
