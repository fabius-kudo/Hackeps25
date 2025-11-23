import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;
import java.io.IOException;
import java.util.List;

class NeighborhoodParks {
    public String name;
    public double lat;
    public double lon;
    public int parks;
}

public class NatureFilter {
    public int getScore(String neighborhoodName) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            // Read as JsonNode first to access the "neighbourhoods" array
            JsonNode root = mapper.readTree(new File("Python2/JSON/park_results.json"));
            JsonNode neighbourhoodsNode = root.get("neighbourhoods");

            // Convert the neighbourhoods array to a List
            List<NeighborhoodParks> parksList = mapper.convertValue(
                    neighbourhoodsNode,
                    new TypeReference<List<NeighborhoodParks>>() {}
            );

            for (NeighborhoodParks np : parksList) {
                if (np.name.equals(neighborhoodName)) {
                    if (np.parks <= 1) {
                        return 1;
                    } else if (np.parks <= 2) {
                        return 2;
                    } else if (np.parks <= 4) {
                        return 3;
                    } else if (np.parks <= 6) {
                        return 4;
                    } else {
                        return 5;
                    }
                }
            }
            return 0;  // Neighborhood not found

        } catch (IOException e) {
            System.err.println("Error reading parks data: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
}