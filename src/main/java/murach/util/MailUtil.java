package murach.util;

public class MailUtil {
    
    public static void sendMail(String to, String from,
            String subject, String body, boolean bodyIsHTML) throws Exception {
        
        // Check if running on Render (has SENDGRID_API_KEY env variable)
        String sendGridKey = System.getenv("SENDGRID_API_KEY");
        
        if (sendGridKey != null && !sendGridKey.isEmpty()) {
            // Use SendGrid for Render deployment
            System.out.println("Using SendGrid API for email...");
            MailUtilSendGrid.sendMail(to, from, subject, body, bodyIsHTML);
        } else {
            // Use Gmail SMTP for local development
            System.out.println("Using Gmail SMTP for email...");
            MailUtilGmail.sendMail(to, from, subject, body, bodyIsHTML);
        }
    }
}
