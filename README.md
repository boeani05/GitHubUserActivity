# GitHub User Activity CLI (Java)

A command-line interface (CLI) application built with Java to fetch and display the recent public activity of a specified GitHub user. This project demonstrates core Java skills in networking, raw JSON parsing using regular expressions, and building robust CLI tools.

## Requirements

The application fulfills the following requirements:

*   **Command-Line Execution:** Runs from the command line, accepting the GitHub username as an argument.
    ```bash
    java -jar github-activity.jar <username>
    ```
*   **GitHub API Integration:** Fetches the user's recent activity using the GitHub API endpoint: `https://api.github.com/users/<username>/events`.
*   **Formatted Output:** Displays the fetched activity in a user-friendly format, similar to:
    ```
    - Pushed 3 commits to kamranahmedse/developer-roadmap
    - Opened a new issue in kamranahmedse/developer-roadmap
    - Starred kamranahmedse/developer-roadmap
    - ...
    ```
*   **Error Handling:** Gracefully handles errors such as invalid usernames (API failures), network issues, or interruptions.
*   **No External Libraries:** Built using only standard Java libraries (no external JSON parsing frameworks like Jackson/Gson).

## How to Run

### Prerequisites

*   Java Development Kit (JDK) 11 or higher installed.

### Steps

1.  **Clone the repository:** (Assuming your project is in a repository)
    ```bash
    git clone <your-repo-url>
    cd <your-project-directory>
    ```
2.  **Compile the Java code:**
    ```bash
    javac UserActivityApp.java UserActivity.java
    ```
3.  **Run the application:**
    ```bash
    java UserActivityApp <github-username>
    ```
    **Example:**
    ```bash
    java UserActivityApp kamranahmedse
    ```

## Example Output
