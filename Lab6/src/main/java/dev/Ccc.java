package dev;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/Ccc")
public class Ccc extends HttpServlet {
    public CBean cBean;

    @Override
    public void init() throws ServletException {
        cBean = new CBean();

        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("atrCBean", cBean);
        System.out.println("init " +servletContext.getAttribute("atrCBean"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("goGet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("CBean").equals("new")) {
            cBean = new CBean();
            ServletContext servletContext = getServletContext();
            servletContext.setAttribute("atrCBean", cBean);
            System.out.println("new: " + servletContext.getAttribute("atrCBean"));

            String param1 = req.getParameter("value1"),
                   param2 = req.getParameter("value2"),
                   param3 = req.getParameter("value3");


            if(param1 != "" || param2!= "" || param3 !=""){
                cBean.setValue1(param1);
                cBean.setValue2(param2);
                cBean.setValue3(param3);
                //PrintWriter printWriter = resp.getWriter();
//                printWriter.println(
//                        "<br>Value1: " + cBean.getValue1() +
//                        "<br>Value2: " + cBean.getValue2() +
//                        "<br>Value3: " + cBean.getValue3()
//                );
            }
            resp.sendRedirect("Cccb.jsp");
        } else if (req.getParameter("CBean").equals("old")){ //if "old"
            ServletContext servletContext = getServletContext();
            System.out.println("old: " + servletContext.getAttribute("atrCBean"));
            cBean = (CBean) servletContext.getAttribute("atrCBean");

            String param1 = req.getParameter("value1"),
                    param2 = req.getParameter("value2"),
                    param3 = req.getParameter("value3");

            String value1 = cBean.getValue1();
            String value2 = cBean.getValue2();
            String value3 = cBean.getValue3();
            System.out.println("old " + value1 + " " + value2 + " " + value3);

            if(param1 != "" || param2!= "" || param3 !=""){
            cBean.setValue1(param1);
            cBean.setValue2(param2);
            cBean.setValue3(param3);
            }
            resp.sendRedirect("Cccb.jsp");
//            PrintWriter printWriter = resp.getWriter();
//            printWriter.println(
//               "<br>Value1: " + value1 +
//               "<br>Value2: " + value2 +
//               "<br>Value3: " + value3
//            );
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
