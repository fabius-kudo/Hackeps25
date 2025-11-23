import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

class NeighborhoodParks {
    public String name;
    public long lat;
    public long lon;
    public int parks;
}

public class NatureFilter {
    public int getScore(String neighborhoodName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<NeighborhoodParks> parksList = mapper.readValue(
                    new File("Python/JSON/parks.json"),
                    new TypeReference<List<NeighborhoodParks>>() {}
            );

            for (NeighborhoodParks np : parksList) {
                if (np.name.equals(neighborhoodName)) {
                    if (np.parks <= 1) {
                        return 1;
                    } else {
                        if (np.parks <= 2) {
                            return 2;
                        } else {
                            if (np.parks <= 4) {
                                return 3;
                            } else {
                                if (np.parks <= 6) {
                                    return 4;
                                } else {
                                    if (np.parks > 6) {
                                        return 5;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return 0;

        } catch (IOException e) {
            System.err.println("Error reading parks data: " + e.getMessage());
            return 0;
        }
    }
}
