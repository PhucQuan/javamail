package murach.util;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;

public class MailUtilSendGrid {
    
    // Get API key from environment variable (set in Render dashboard)
    private static final String SENDGRID_API_KEY = System.getenv("SENDGRID_API_KEY");
    
    public static void sendMail(String to, String from,
            String subject, String body, boolean bodyIsHTML) throws IOException {
        
        Email fromEmail = new Email(from);
        Email toEmail = new Email(to);
        
        Content content;
        if (bodyIsHTML) {
            content = new Content("text/html", body);
        } else {
            content = new Content("text/plain", body);
        }
        
        Mail mail = new Mail(fromEmail, subject, toEmail, content);
        
        SendGrid sg = new SendGrid(SENDGRID_API_KEY);
        Request request = new Request();
        
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            
            System.out.println("SendGrid Status Code: " + response.getStatusCode());
            System.out.println("SendGrid Response: " + response.getBody());
        } catch (IOException ex) {
            throw ex;
        }
    }
}
