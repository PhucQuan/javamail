package murach.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class MailUtilBrevo {
    
    private static final String BREVO_API_URL = "https://api.brevo.com/v3/smtp/email";
    private static final String BREVO_API_KEY = System.getenv("BREVO_API_KEY");
    
    public static void sendMail(String to, String from, String subject, String body, boolean bodyIsHTML) 
            throws Exception {
        
        // Validate API key
        if (BREVO_API_KEY == null || BREVO_API_KEY.trim().isEmpty()) {
            throw new Exception("BREVO_API_KEY environment variable is not set");
        }
        
        // Build JSON payload
        String jsonPayload = buildJsonPayload(to, from, subject, body, bodyIsHTML);
        
        // Create HTTP client
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        
        // Create HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BREVO_API_URL))
                .header("accept", "application/json")
                .header("api-key", BREVO_API_KEY)
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .timeout(Duration.ofSeconds(30))
                .build();
        
        // Send request
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        // Check response
        if (response.statusCode() == 201) {
            System.out.println("Email sent successfully via Brevo API");
            System.out.println("Response: " + response.body());
        } else {
            String errorMsg = "Failed to send email. Status: " + response.statusCode() + 
                            ", Response: " + response.body();
            System.err.println(errorMsg);
            throw new Exception(errorMsg);
        }
    }
    
    private static String buildJsonPayload(String to, String from, String subject, String body, boolean bodyIsHTML) {
        // Escape special characters in JSON strings
        String escapedSubject = escapeJson(subject);
        String escapedBody = escapeJson(body);
        String escapedTo = escapeJson(to);
        String escapedFrom = escapeJson(from);
        
        // Build JSON manually to avoid external dependencies
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"sender\":{\"email\":\"").append(escapedFrom).append("\"},");
        json.append("\"to\":[{\"email\":\"").append(escapedTo).append("\"}],");
        json.append("\"subject\":\"").append(escapedSubject).append("\",");
        
        if (bodyIsHTML) {
            json.append("\"htmlContent\":\"").append(escapedBody).append("\"");
        } else {
            json.append("\"textContent\":\"").append(escapedBody).append("\"");
        }
        
        json.append("}");
        
        return json.toString();
    }
    
    private static String escapeJson(String str) {
        if (str == null) {
            return "";
        }
        return str.replace("\\", "\\\\")
                  .replace("\"", "\\\"")
                  .replace("\n", "\\n")
                  .replace("\r", "\\r")
                  .replace("\t", "\\t");
    }
}

