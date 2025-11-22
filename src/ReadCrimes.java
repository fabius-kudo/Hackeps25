import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ReadCrimes {
    public static void readCrimes () {
        try {
            String json = getJsonString();
            Map<String, Integer> counts = new HashMap<>();

            int index = 0;

            while (true) {
                index = json.indexOf("\"area_name\"", index);
                if (index == -1) break;
                
                int colon = json.indexOf(":", index);
                int quote1 = json.indexOf("\"", colon + 1);
                int quote2 = json.indexOf("\"", quote1 + 1);

                String area = json.substring(quote1 + 1, quote2);

                counts.put(area, counts.getOrDefault(area, 0) + 1);

                index = quote2 + 1;
            }

            System.out.println("Distinct cities: " + counts.size());
            counts.forEach((k, v) -> System.out.println(k + " → " + v));
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static String getJsonString() throws IOException {
        URL url = new URL("https://data.lacity.org/resource/2nrs-mtv8.json");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder jsonString = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null){
            jsonString.append(line);
        }

        reader.close();
        connection.disconnect();

        return jsonString.toString();
    }
}
