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
                    new File("Python/JSON/crimes.json"),
                    new TypeReference<List<NeighborhoodCrimes>>() {}
            );

            for (NeighborhoodCrimes np : crimesList) {
                if (np.name.equals(neighborhoodName)) {
                    if (np.crimes <= 60) {
                        return 1;
                    } else {
                        if (np.crimes <= 60) {
                            return 2;
                        } else {
                            if (np.crimes <= 40) {
                                return 3;
                            } else {
                                if (np.crimes <= 20) {
                                    return 4;
                                } else {
                                    return 5;
                                }

                            }

                        }
                }
            }
            return 0;

        } catch (IOException e) {
            System.err.println("Error reading safety data: " + e.getMessage());
            return 0;
        }
    }
}
