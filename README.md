# GitHub User Activity CLI (Java)

A command-line interface (CLI) application built with Java to fetch and display the recent public activity of a
specified GitHub user. This project demonstrates core Java skills in networking, raw JSON parsing using regular
expressions, and building robust CLI tools.

## Requirements

The application fulfills the following requirements:

* **Command-Line Execution:** Runs from the command line, accepting the GitHub username as an argument.
  ```bash
  java -jar github-activity.jar <username>
  ```
* **GitHub API Integration:** Fetches the user's recent activity using the GitHub API endpoint:
  `https://api.github.com/users/<username>/events`.
* **Formatted Output:** Displays the fetched activity in a user-friendly format, similar to:
  ```
  - Pushed 3 commits to kamranahmedse/developer-roadmap
  - Opened a new issue in kamranahmedse/developer-roadmap
  - Starred kamranahmedse/developer-roadmap
  - ...
  ```
* **Error Handling:** Gracefully handles errors such as invalid usernames (API failures), network issues, or
  interruptions.
* **No External Libraries:** Built using only standard Java libraries (no external JSON parsing frameworks like
  Jackson/Gson).

## How to Run

### Prerequisites

* Java Development Kit (JDK) 11 or higher installed.

### Steps

1. **Clone the repository:** (Assuming your project is in a repository)
   ```bash
   git clone https://github.com/boeani05/GitHubUserActivity.git
   cd GitHubUserActivity
   ```
2. **Compile the Java code:**
   ```bash
   javac UserActivityApp.java UserActivity.java
   ```
3. **Run the application:**
   ```bash
   java UserActivityApp <github-username>
   ```
   **Example:**
   ```bash
   java UserActivityApp kamranahmedse
   ```

## Example Output

* Pushed 3 commit(s) to kamranahmedse/timelang
* Created a branch in kamranahmedse/timelang (unknown Event)
* Pushed 1 commit(s) to kamranahmedse/claude-run
* Created an issue in kamranahmedse/claude-run
* Closed an issue in kamranahmedse/claude-run
  *(Note: Output may vary based on actual GitHub user activity and minor API response differences.)*

## Technologies Used

* **Java 11+**
* `java.net.http.HttpClient`: For making HTTP requests to the GitHub API.
* `java.util.regex.Pattern` and `java.util.regex.Matcher`: For robust, manual parsing of the JSON response body.

## Challenges & Learning

This project provided valuable hands-on experience in several key areas:

* **API Integration:** Understanding and interacting with a RESTful API (GitHub API) using standard Java networking
  utilities.
* **Manual JSON Parsing:** A deep dive into extracting specific data from a complex JSON string solely using Java's
  `String` methods and regular expressions. This was a particularly challenging aspect, highlighting the power and
  precision required for regex when external JSON libraries are not permitted.
* **Custom Logic Implementation:** Developing advanced logic for data aggregation, specifically counting and summarizing
  consecutive "PushEvent" commits to the same repository.
* **Robust Error Handling:** Implementing comprehensive `try-catch` blocks for network-related exceptions (
  `IOException`, `URISyntaxException`, `InterruptedException`) to ensure application stability.
* **CLI Development:** Building a functional command-line interface capable of taking arguments and presenting formatted
  output.

**AI Assistance:** This project was developed with the active guidance of an AI learning assistant provided by
roadmap.sh. The AI played a crucial role in breaking down complex problems into manageable steps, suggesting effective
strategies for regex construction, debugging logical flow, and refining code structure, which significantly enhanced the
learning process.

## Future Enhancements

* Support for more GitHub event types with detailed output (e.g., PullRequestReviewEvent, CreateEvent,
  IssueCommentEvent).
* Add options for filtering events by type or date.
* Implement pagination for fetching more than the default number of events.
* Cache fetched data to reduce API calls and improve performance.
* Use an external JSON parsing library (e.g., Jackson, Gson) for easier and more robust JSON handling in real-world
  scenarios.
