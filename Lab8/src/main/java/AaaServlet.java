import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.sun.net.httpserver.Headers;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

@WebServlet(name = "AaaServlet")
public class AaaServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpClient hc = new HttpClient();
        PostMethod pm = new PostMethod("http://localhost:8080/Lab8_war/Bbb");

        pm.addParameter("name", "Alina");
        pm.addParameter("surname", "Punko");
        pm.addParameter("secondName", "Andreevna");

        pm.addRequestHeader("header1", "value1");
        pm.addRequestHeader("header2", "value2");
        hc.executeMethod(pm);

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Header[] headers = pm.getResponseHeaders();
        for (Header header : headers) {
            pw.println(header.getName()+ ": " + header.getValue());
        }
        pw.println("Body: " + pm.getResponseBodyAsString());
        pw.flush();
    }
}
