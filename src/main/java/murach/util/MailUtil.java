package murach.util;

public class MailUtil {
    
    public static void sendMail(String to, String from,
            String subject, String body, boolean bodyIsHTML) throws Exception {
        
        // Auto-detect which email service to use based on environment variables
        
        if (System.getenv("SENDGRID_API_KEY") != null) {
            System.out.println("Using SendGrid API...");
            MailUtilSendGrid.sendMail(to, from, subject, body, bodyIsHTML);
        } 
        else if (System.getenv("MAILGUN_API_KEY") != null) {
            System.out.println("Using Mailgun API...");
            MailUtilMailgun.sendMail(to, from, subject, body, bodyIsHTML);
        }
        else if (System.getenv("RESEND_API_KEY") != null) {
            System.out.println("Using Resend API...");
            MailUtilResend.sendMail(to, from, subject, body, bodyIsHTML);
        }
        else if (System.getenv("BREVO_API_KEY") != null) {
            System.out.println("Using Brevo API...");
            MailUtilBrevo.sendMail(to, from, subject, body, bodyIsHTML);
        }
        else {
            // Use Gmail SMTP for local development
            System.out.println("Using Gmail SMTP...");
            MailUtilGmail.sendMail(to, from, subject, body, bodyIsHTML);
        }
    }
}
