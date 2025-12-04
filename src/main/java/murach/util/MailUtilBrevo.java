package murach.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MailUtilBrevo {
    
    private static final String BREVO_API_KEY = System.getenv("BREVO_API_KEY");
    
    public static void sendMail(String to, String from,
            String subject, String body, boolean bodyIsHTML) throws IOException, InterruptedException {
        
        String url = "https://api.brevo.com/v3/smtp/email";
        
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
