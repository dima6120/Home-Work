/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * SendMail
 * @author dima6120
 */

package sendmail;

import java.util.*;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;


public class SendMail {
    private static String HOST = "smtp.gmail.com";
    private static String USER = "dima6120@gmail.com";
    private static String PASSWORD = "";
    private static String PORT = "465";
    private static String FROM = "TempConv";
    private static String TO = "";

    private static String STARTTLS = "true";
    private static String AUTH = "true";
    private static String DEBUG = "true";
    private static String SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private static String SUBJECT = "TempConvResults";
    private static String TEXT = "All right!";
    
    public static synchronized boolean send(String mes[], String addr) {
        TO = addr;
        
        Properties props = new Properties();
    
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.user", USER);

        props.put("mail.smtp.auth", AUTH);
        props.put("mail.smtp.starttls.enable", STARTTLS);
        props.put("mail.smtp.debug", DEBUG);

        props.put("mail.smtp.socketFactory.port", PORT);
        props.put("mail.smtp.socketFactory.class", SOCKET_FACTORY);
        props.put("mail.smtp.socketFactory.fallback", "false");

        try {
            Session session = Session.getDefaultInstance(props, null);
            session.setDebug(true);

            MimeMessage message = new MimeMessage(session);
            message.setSentDate(new Date());
            message.setText(TEXT);
            message.setSubject(SUBJECT);
            message.setFrom(new InternetAddress(FROM));
            message.addRecipient(RecipientType.TO, new InternetAddress(TO));
            message.saveChanges();

            MimeBodyPart p1;
            
            Multipart mp = new MimeMultipart();
            for(String s : mes) {
                p1 = new MimeBodyPart();
                p1.setText(s);
                mp.addBodyPart(p1);
            }

            message.setContent(mp);
            
            
            Transport transport = session.getTransport("smtp");
            transport.connect(HOST, USER, PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;

        } catch (Exception e) {
            System.err.println("Fail to send mail!");
            return false;
        }

    }
}
