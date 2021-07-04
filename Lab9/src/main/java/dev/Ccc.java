package dev;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/Ccc")
public class Ccc extends HttpServlet {
    public CBean cBean;

    @Override
    public void init() throws ServletException {
        cBean = new CBean();
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
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("atrCBean", cBean);
            System.out.println("new: " + httpSession.getAttribute("atrCBean"));

            String param1 = req.getParameter("value1"),
                    param2 = req.getParameter("value2"),
                    param3 = req.getParameter("value3");


            if (param1 != "" || param2 != "" || param3 != "") {
                cBean.setValue1(param1);
                cBean.setValue2(param2);
                cBean.setValue3(param3);
            }
            RequestDispatcher rd =  req.getRequestDispatcher("Cccb.jsp");
            rd.forward(req, resp);
        } else if (req.getParameter("CBean").equals("old")){ //if "old"
            HttpSession httpSession = req.getSession();
            System.out.println("old: " + httpSession.getAttribute("atrCBean"));
            cBean = (CBean) httpSession.getAttribute("atrCBean");

            if(cBean == null){
                cBean = new CBean();
                req.setAttribute("atrCBean", cBean);
            }
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
            RequestDispatcher rd =  req.getRequestDispatcher("Cccb.jsp");
            rd.forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
