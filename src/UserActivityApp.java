import java.io.IOException;
import java.net.URISyntaxException;

public class UserActivityApp {
    public static void main(String[] args) {
        UserActivity userActivity = new UserActivity();

        while (true) {
            try {
                userActivity.fetchRecentActivity(args[0]);
                break;
            } catch (IOException e) {
                System.err.println("Something went wrong, please try again!");
            } catch (URISyntaxException f) {
                System.err.println("URI could not be parsed. Please try again!");
            } catch (InterruptedException g) {
                System.err.println("Thread was interrupted unexpectedly!");
            }
        }
    }
}