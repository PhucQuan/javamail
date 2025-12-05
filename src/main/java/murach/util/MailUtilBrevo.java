package murach.util;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class MailUtilBrevo {
    
    private static final String SMTP_HOST = "smtp-relay.brevo.com";
    private static final String SMTP_PORT = "587";
    private static final String SMTP_USERNAME = System.getenv("BREVO_EMAIL");
    private static final String SMTP_PASSWORD = System.getenv("BREVO_SMTP_KEY");
    
    public static void sendMail(String to, String from,
            String subject, String body, boolean bodyIsHTML) throws MessagingException {
        
        // Set SMTP properties
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", SMTP_HOST);
        
        // Get session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
            }
        });
        String jsonBody = String.format(
            "{\"sender\":{\"email\":\"%s\"},\"to\":[{\"email\":\"%s\"}],\"subject\":\"%s\",\"textContent\":\"%s\"}",
            from, to, subject, body.replace("\n", "\\n")
        );
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("api-key", BREVO_API_KEY)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Brevo Status: " + response.statusCode());
        System.out.println("Brevo Response: " + response.body());
    }
}
