import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class UserActivityApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserActivity userActivity = new UserActivity();
        String gitHubUserName;

        System.out.println("=== Enter the GitHub-Username you would like to see the activity of ===");

        gitHubUserName = scanner.nextLine();

        while (true) {
            try {
                userActivity.fetchRecentActivity(gitHubUserName);
                break;
            } catch (IOException e) {
                System.err.println("Something went wrong, please try again!");
                gitHubUserName = scanner.nextLine();
            } catch (URISyntaxException f) {
                System.err.println("URI could not be parsed. Please try again!");
                gitHubUserName = scanner.nextLine();
            } catch (InterruptedException g) {
                System.err.println("Thread was interrupted unexpectedly!");
            }
        }
    }
}