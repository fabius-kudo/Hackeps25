import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

class NeighborhoodRent {
    public String name;
    public int rent;
}

public class NatureFilter {
    public int getScore(String neighborhoodName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<NeighborhoodParks> rentList = mapper.readValue(
                    new File("Python/JSON/rent.json"),
                    new TypeReference<List<NeighborhoodRent>>() {}
            );

            for (NeighborhoodParks np : rentList) {
                if (np.name.equals(neighborhoodName)) {
                    if (np.rent >= 30000 ) {
                        return 1;
                    } else {
                        if (np.rent >= 20000) {
                            return 2;
                        } else {
                            if (np.rent >= 10000) {
                                return 3;
                            } else {
                                if (np.rent >= 5000) {
                                    return 4;
                                } else {
                                    return 5;
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
