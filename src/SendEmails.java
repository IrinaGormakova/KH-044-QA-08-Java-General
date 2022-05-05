import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmails {

    private String from = "gormakova.ira@gmail.com"; // Sender's email ID needs to be mentioned
    private String password = "*******"; // Sender's email ID needs to be mentioned
    private String author = "Author";
    private final String host = "smtp.gmail.com";          // Send email from through gmails smtp

    public SendEmails(String fromEmail, String password, String author) {
        this.from = fromEmail;
        this.password = password;
        this.author = author;
    }

    private Properties setSystemProperties() {
        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
/*
        //TLS
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", host);
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
*/
        //  /*
        //SSL
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.trust", host);
        //  */
        return properties;
    }//end setSystemProperties

    private Session getSession(Properties setProperties) {
        // Get the Session object.// and pass username and password
        return Session.getInstance(setProperties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(from, password);
            }
        });
    }//end getSession

    public void sendNotification(Task assignedTask) {

        // Recipient's email ID needs to be mentioned.
        // String to = assignedTask.getEmail();
        String to = "gormakova.ira@gmail.com";

        // Get system properties
        Properties prop = setSystemProperties();

        // Get the Session object.// and pass username and password
        Session session = getSession(prop);

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(from));

            // Set Subject: header field
            message.setSubject("Assigned task ID " + assignedTask.getID());

            // Set the actual message
            String htmlContent = "<p>Dear, " + author + "</p>"
                    + "<p>You can find csv report in attachment</p>"
                    + "<p>task ID: " + assignedTask.getID() + "</p>"
                    + "<p>priority: " + assignedTask.getPriority() + "</p>"
                    + "<p>status: " + assignedTask.getStatus() + "</p>"
                    + "<p>created: " + assignedTask.getCreationDate() + "</p>"
                    + "<p>should be completed till: " + assignedTask.getExecutionDate() + "</p>"
                    + "<p>" + assignedTask.getBody() + "</p>";
            BodyPart mimeBody = new MimeBodyPart();
            mimeBody.setContent(htmlContent, "text/html");
            Multipart multiPart = new MimeMultipart();
            multiPart.addBodyPart(mimeBody);

            message.setContent(multiPart);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent notification successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }//end sendEmail


    public void sendCsvReport(String filePath) {


        // Get system properties
        Properties prop = setSystemProperties();

        // Get the Session object.// and pass username and password
        Session session = getSession(prop);

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(from));

            // Set Subject: header field
            message.setSubject("Report csv");

            // Set the actual message
            String htmlContent = "<p>Dear, " + author + "</p>"
                    + "<p>You can find csv report in attachment</p>";
            MimeBodyPart textBody = new MimeBodyPart();
            MimeBodyPart attachmentPart = new MimeBodyPart();

            textBody.setContent(htmlContent, "text/html");
            Multipart multiPart = new MimeMultipart();

            try {
                File f = new File(filePath);
                if (!f.exists()) {
                    try {
                        f.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                attachmentPart.attachFile(f);
                multiPart.addBodyPart(textBody);
                multiPart.addBodyPart(attachmentPart);

            } catch (IOException e) {
                e.printStackTrace();
            }

            message.setContent(multiPart);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
}//end class

