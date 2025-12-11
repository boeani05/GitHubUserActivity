import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserActivity {
    public void fetchRecentActivity(String userName) throws IOException, URISyntaxException, InterruptedException {
        String urlAddress = String.format("https://api.github.com/users/%s/events", userName);
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest newRequest = HttpRequest.newBuilder(new URI(urlAddress))
                    .GET()
                    .build();
            HttpResponse<String> httpResponse = httpClient.send(newRequest, HttpResponse.BodyHandlers.ofString());

            String httpResponseBody = httpResponse.body();

            httpResponseBody = httpResponseBody.replaceAll("\\[", "");

            int eventTypeIndex;
            Pattern pattern = Pattern.compile("\"type\": \"");
            if (new Matcher().matches(pattern)) {
                eventTypeIndex = httpResponseBody.indexOf(8);
            }
        }
    }
}
