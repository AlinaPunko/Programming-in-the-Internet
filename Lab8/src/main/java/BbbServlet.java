import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "BbbServlet")
public class BbbServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String name = request.getParameterValues("name")[0];
//        String surname = request.getParameterValues("surname")[0];
//        String secondName = request.getParameterValues("secondName")[0];

        String params = "Params: ";
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()){
            String paramName = paramNames.nextElement();
            params+= " " + paramName + ": " + request.getParameter(paramName);
        }

//        String header1 = request.getHeader("header1");
//        String header2 = request.getHeader("header2");

        String headers = "Headers: ";
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            headers += " " + headerName + ": " + request.getHeader(headerName);
        }

        response.setHeader("responseHeader1", "responseHeader1-value");
        response.setHeader("responseHeader2", "responseHeader2-value");

//        response.getWriter().println("params: " + name + " " + surname + " "+ secondName + " headers " + header1 + " " + header2);
        response.getWriter().println(params + " /////// " + headers);
    }
}
