import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "Files", urlPatterns = "/files")
public class Sss extends HttpServlet {

    BufferedWriter writer;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.getWriter().println("Servlet:SSS");

        String filename = req.getParameter("file");

        String docdir   = getServletContext().getInitParameter("doc-dir");

        if (filename == null) {
            Finder files = new Finder(docdir, "docx");
            resp.setContentType("text/html");
            StringBuilder result = new StringBuilder();
            result.append("<hr>");
            for (String file : files.list) {
                result.append("<br>").append("<a href='/Lab13_war/files?file=").append(file).append("' >").append(file).append("</a>");
            }
            result.append("<hr>");

            resp.getWriter().println(result);
        } else {
            System.out.println(filename);
            lofFile(filename);
            try {
                File doc = new File(docdir.concat("\\").concat(filename));
                resp.setContentType("application/msword");
                resp.addHeader("Content-Disposition", "attachment; filename="+ doc.getName());
                resp.setContentLength((int) doc.length());

                FileInputStream in = new FileInputStream(doc);
                BufferedInputStream buf = new BufferedInputStream(in);
                ServletOutputStream out = resp.getOutputStream();
                int readBytes = 0;
                while ((readBytes = buf.read()) != -1) {
                    out.write(readBytes);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }

    }

    public  void  lofFile(String fileName) throws IOException {
        writer = new BufferedWriter(new FileWriter("D:\\лабы\\ПвИ\\Lab13\\src\\main\\webapp\\stdout.txt"));
        writer.append(fileName);
        writer.append("\n");
        writer.close();
    }
}
