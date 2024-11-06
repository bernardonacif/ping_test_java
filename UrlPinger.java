import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlPinger {
    public static void main(String[] args) {
        String urlString = "http://example.com"; // Replace with your target URL
        pingUrl(urlString);
    }

    public static void pingUrl(String urlString) {
        try {
            URL url = new URL(urlString);
            long startTime = System.currentTimeMillis();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); // Set timeout (optional)
            connection.setReadTimeout(5000);    // Set read timeout (optional)

            int responseCode = connection.getResponseCode();
            long endTime = System.currentTimeMillis();

            System.out.println("Status Code: " + responseCode);
            System.out.println("Response Time: " + (endTime - startTime) + " ms");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
