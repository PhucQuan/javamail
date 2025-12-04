package murach.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MailUtilResend {
    
    private static final String RESEND_API_KEY = System.getenv("RESEND_API_KEY");
    
    public static void sendMail(String to, String from,
            String subject, String body, boolean bodyIsHTML) throws IOException, InterruptedException {
        
        String url = "https://api.resend.com/emails";
        
        String jsonBody = String.format(
            "{\"from\":\"%s\",\"to\":[\"%s\"],\"subject\":\"%s\",\"text\":\"%s\"}",
            from, to, subject, body.replace("\n", "\\n")
        );
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + RESEND_API_KEY)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Resend Status: " + response.statusCode());
        System.out.println("Resend Response: " + response.body());
    }
}
