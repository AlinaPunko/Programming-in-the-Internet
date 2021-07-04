import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("Called Service in Sss");
//        response.getWriter().println(request.getQueryString());
        if(request.getMethod().equals("POST")){
            System.out.println("Called POST in Sss");
                //response.getWriter().println(request.getParameter("name") + " " +  request.getParameter("surname"));
        }
        else if(request.getMethod().equals("GET")){
            System.out.println("Called GET in Sss");
//            response.getWriter().println(request.getQueryString());
        }
        StringBuilder buffer = new StringBuilder();
        BufferedReader br = request.getReader();
        String line;
        while ((line = br.readLine()) != null) {
            buffer.append(line);
        }
//        String data = buffer.toString();
//        response.getWriter().println(data);

        //RequestDispatcher rd = request.getRequestDispatcher("/GoGgg");
           //rd.forward(request, response);

        HttpClient hc = new HttpClient();
        GetMethod gm = new GetMethod("http://172.16.193.69:8080/AS_PAA_13_3/GoGgg?name=Alina&surname=Punko");
        PostMethod pm = new PostMethod("http://172.16.193.69:8080/AS_PAA_13_3/GoGgg");

        pm.addParameter("name", "Alina");
        pm.addParameter("surname", "Punko");

        NameValuePair[] nameValuePairs = {
                new NameValuePair("name", "Alina"),
                new NameValuePair("surname", "Punko")
        };
        pm.setRequestBody(nameValuePairs);

        hc.executeMethod(gm);
        //hc.executeMethod(pm);

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println(gm.getResponseBodyAsString());
        //pw.println(pm.getResponseBodyAsString());
        pw.flush();

//        response.sendRedirect("/Lab3_war/GoGgg");
    }
}
