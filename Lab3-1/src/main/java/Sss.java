import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

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
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Called Service in Ggg");
        if(request.getMethod().equals("POST")){
            System.out.println("Called POST in Ggg");
//            response.getWriter().println(request.getParameter("name") + " " +  request.getParameter("surname"));
        }
        else if(request.getMethod().equals("GET")){
            System.out.println("Called GET in Ggg");
//            response.getWriter().println(request.getQueryString());
        }
//        RequestDispatcher rd = request.getRequestDispatcher("/GoGgg");
//           rd.forward(request, response);

        HttpClient hc = new HttpClient();
        GetMethod gm = new GetMethod("http://localhost:8080/GoGgg?name=Alina&surname=Punko");

        hc.executeMethod(gm);
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println(gm.getResponseBodyAsString());
        pw.flush();

        response.sendRedirect("/Lab3_war/GoGgg");
    }
}
