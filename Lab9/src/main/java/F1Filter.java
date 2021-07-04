import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "F1Filter", urlPatterns = "/Ccc")
public class F1Filter implements Filter {
    public void destroy() {
        System.out.println("F1: destroy");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("F1: doFilter");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("F1: init");
    }

}
