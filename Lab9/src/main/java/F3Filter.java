import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "F3Filter", urlPatterns = "/Ccc")
public class F3Filter implements Filter {
    public void destroy() {
        System.out.println("F3: destroy");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("F3: doFilter");
        resp.getWriter().print("Stop");
        //chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("F3: init");
    }

}
