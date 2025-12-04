package murach.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class MailUtilMailgun {
    
    private static final String MAILGUN_API_KEY = System.getenv("MAILGUN_API_KEY");
    private static final String MAILGUN_DOMAIN = System.getenv("MAILGUN_DOMAIN");
    
    public static void sendMail(String to, String from,
            String subject, String body, boolean bodyIsHTML) throws IOException, InterruptedException {
        
        String url = "https://api.mailgun.net/v3/" + MAILGUN_DOMAIN + "/messages";
        
        String formData = "from=" + from +
                         "&to=" + to +
                         "&subject=" + subject +
                         "&text=" + body;
        
        String auth = "api:" + MAILGUN_API_KEY;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Basic " + encodedAuth)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(formData))
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Mailgun Status: " + response.statusCode());
        System.out.println("Mailgun Response: " + response.body());
    }
}
