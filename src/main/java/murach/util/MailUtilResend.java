package murach.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MailUtilResend {
    
    private static final String RESEND_API_KEY = System.getenv("RESEND_API_KEY");
    private static final String DEFAULT_FROM = "Murach App <onboarding@resend.dev>";
    
    public static void sendMail(String to, String from,
            String subject, String body, boolean bodyIsHTML) throws IOException, InterruptedException {
        
        if (RESEND_API_KEY == null || RESEND_API_KEY.isEmpty()) {
            throw new IOException("RESEND_API_KEY is not set in environment variables");
        }
        
        // Use default from address if not provided
        String fromAddress = (from != null && !from.isEmpty()) ? from : DEFAULT_FROM;
        
        String url = "https://api.resend.com/emails";
        
        // Escape special characters in the body
        String escapedBody = body.replace("\"", "\\\"")
                               .replace("\n", "\\n")
                               .replace("\r", "");
        
        String jsonBody = String.format(
            "{\"from\":\"%s\",\"to\":[\"%s\"],\"subject\":\"%s\",\"text\":\"%s\"}",
            fromAddress, to, subject, escapedBody
        );
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + RESEND_API_KEY)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            System.out.println("Resend Status: " + response.statusCode());
            System.out.println("Resend Response: " + response.body());
            
            if (response.statusCode() >= 400) {
                throw new IOException("Failed to send email: " + response.body());
            }
        } catch (Exception e) {
            System.err.println("Error sending email via Resend: " + e.getMessage());
            throw e;
        }
    }
}
