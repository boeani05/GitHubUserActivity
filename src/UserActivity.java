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
        Pattern basicPattern;
        Pattern pullRequestPattern;
        Pattern watchEventPattern;
        Matcher basicMatcher;
        Matcher pullRequestMatcher;
        Matcher watchEventMatcher;
        String eventType;
        String repoName;
        String currentRepoName = null;
        String currentJsonBlock;
        String capitalizedPullRequestAction;
        int counter;

        basicPattern = Pattern.compile("\"type\":\\s*\"([^\"]*)\".*?\"repo\":\\s*\\{.*?\"name\":\\s*\"([^\"]*)\".*?}(?=\\s*,\\s*\\{\"id\":|]$)");
        pullRequestPattern = Pattern.compile(".*?\"name\":\"([^\"]*).*?\"action\":\"(\\w+)\"");
        watchEventPattern = Pattern.compile(".*?\"name\":\"([^\"]*)");


        String urlAddress = String.format("https://api.github.com/users/%s/events", userName);
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest newRequest = HttpRequest.newBuilder(new URI(urlAddress)).GET().build();
            HttpResponse<String> httpResponse = httpClient.send(newRequest, HttpResponse.BodyHandlers.ofString());

            String httpResponseBody = httpResponse.body();
            basicMatcher = basicPattern.matcher(httpResponseBody);
            counter = 0;

            while (basicMatcher.find()) {
                currentJsonBlock = basicMatcher.group(0);
                eventType = basicMatcher.group(1);
                repoName = basicMatcher.group(2);

                if ("PushEvent".equals(eventType)) {
                    if (counter > 0 && repoName.equals(currentRepoName)) {
                        counter++;
                    } else {
                        if (counter > 0) {
                            System.out.printf("- Pushed %d commit(s) to %s%n", counter, currentRepoName);
                        }

                        currentRepoName = repoName;
                        counter = 1;
                    }
                } else {
                    if (counter > 0) {
                        System.out.printf("- Pushed %d commit(s) to %s%n", counter, currentRepoName);
                        counter = 0;
                        currentRepoName = null;
                    }
                }
                if ("PullRequestEvent".equals(eventType)) {
                    String pullRequestAction;
                    String pullRequestName;

                    pullRequestMatcher = pullRequestPattern.matcher(currentJsonBlock);

                    if (pullRequestMatcher.find()) {
                        pullRequestName = pullRequestMatcher.group(1);
                        pullRequestAction = pullRequestMatcher.group(2);
                        capitalizedPullRequestAction = pullRequestAction.substring(0, 1).toUpperCase() + pullRequestAction.substring(1);

                        System.out.printf("- %s a pull request in %s%n", capitalizedPullRequestAction, pullRequestName);
                    }
                } else if ("WatchEvent".equals(eventType)) {
                    String watchEventName;

                    watchEventMatcher = watchEventPattern.matcher(currentJsonBlock);

                    if (watchEventMatcher.find()) {
                        watchEventName = watchEventMatcher.group(1);

                        System.out.printf("- Starred %s%n", watchEventName);
                    }
                }
            }
        }
    }
}