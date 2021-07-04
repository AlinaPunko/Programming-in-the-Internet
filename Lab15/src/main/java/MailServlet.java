import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;

@WebServlet(name = "mail", urlPatterns = "/mail")
public class MailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        String eMailAddressTo = "punko.alina2000@gmail.com";
        String eMailMessage = request.getParameter("message");
        String eMailMessageSubject = context.getInitParameter("eMailMessageSubject");

        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("punko.alina2000@gmail.com", "31415926Punko");
            }
        };
        Session session = Session.getInstance(props, auth);

        MailSender.sendEmail(session, eMailAddressTo, eMailMessageSubject, eMailMessage);
}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = "punko.alina.2000@gmail.com";
            String password = "31415926Punko";
            String result = MailSender.getMessages(username, password);

            resp.getWriter()
                    .println(result);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
