import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class MailSender {
    public static void sendEmail(Session session, String toEmail, String subject, String body){
        try
        {
            MimeMessage msg = new MimeMessage(session);

            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("punko.alina2000@gmail.com", "Alina Punko"));

            msg.setSubject(subject, "UTF-8");
            msg.setText(body, "UTF-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

            Transport.send(msg);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getMessages(final String user, final String password) throws MessagingException, IOException {
        String host = "pop.gmail.com";
        String port = "995";

        Properties properties = new Properties();

        properties.put("mail.pop3.host", host);
        properties.put("mail.pop3.port", port);
        properties.put("mail.pop3.starttls.enable", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        Store store = session.getStore("pop3s");

        store.connect(host, user, password);
        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);

        Message[] messages = folder.getMessages();

        StringBuilder result = new StringBuilder();

        // Create html result (array of eMail messages)
        for (Message message: messages) {
            result.append("<div>");
            result.append("<div>").append(InternetAddress.toString(message.getFrom())).append("</div>");
            result.append("<div>").append(message.getSubject()).append("</div>");
            result.append("<div>").append(message.getContent()).append("</div>");
            result.append("<div>").append(message.getSentDate()).append("</div>");
            result.append("</div>");
            result.append("___________________________");
        }

        folder.close(true);
        store.close();

        return result.toString();
    }
}
