import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UserActivity {
    public void fetchRecentActivity(String userName) throws IOException, URISyntaxException, InterruptedException {
        String urlAddress = String.format("https://api.github.com/users/%s/events", userName);
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest newRequest = HttpRequest.newBuilder(new URI(urlAddress))
                    .GET()
                    .build();
            HttpResponse<String> httpResponse = httpClient.send(newRequest, HttpResponse.BodyHandlers.ofString());

            String httpResponseBody = httpResponse.body();

            String[] splitType = httpResponseBody.split("\"type\": *");


            int startQuote;
            int endQuote;
            String event;

            if (splitType.length > 1) {
                startQuote = splitType[1].indexOf("\"");
                endQuote = splitType[1].indexOf("\"", startQuote + 1);
                event = splitType[1].substring(startQuote + 1, endQuote);
                System.out.println(event);
            }
        }
    }
}
