import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

class NeighborhoodCrimes {
    public String name;
    public int crimes;
}

public class SafetyFilter {
    public int getScore(String neighborhoodName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<NeighborhoodCrimes> crimesList = mapper.readValue(
                    new File("Python2/JSON/crimes.json"),
                    new TypeReference<List<NeighborhoodCrimes>>() {}
            );

            for (NeighborhoodCrimes nc : crimesList) {
                if (nc.name.equals(neighborhoodName)) {
                    // Lower crimes = higher safety score
                    if (nc.crimes <= 20) {
                        return 5;  // Very safe
                    } else if (nc.crimes <= 40) {
                        return 4;
                    } else if (nc.crimes <= 60) {
                        return 3;
                    } else if (nc.crimes <= 80) {
                        return 2;
                    } else {
                        return 1;  // Least safe
                    }
                }
            }
            return 0;  // Neighborhood not found

        } catch (IOException e) {
            System.err.println("Error reading safety data: " + e.getMessage());
            return 0;
        }
    }
}