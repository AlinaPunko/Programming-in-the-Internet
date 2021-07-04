import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "F2Filter")
public class F2Filter implements Filter {
    public void destroy() {
        System.out.println("F2: destroy");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("F2: doFilter");
        //req.getRequestDispatcher("/index.jsp").forward(req, resp);
        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("F2: init");
    }

}
