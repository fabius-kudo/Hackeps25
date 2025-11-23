import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

class NeighborhoodRent {
    public String name;
    public int rent;
}

public class RentFilter {
    public int getScore(String neighborhoodName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<NeighborhoodRent> rentList = mapper.readValue(
                    new File("Python2/JSON/rent.json"),
                    new TypeReference<List<NeighborhoodRent>>() {}
            );

            for (NeighborhoodRent np : rentList) {
                if (np.name.equals(neighborhoodName)) {
                    if (np.rent >= 3000 ) {
                        return 1;
                    } else {
                        if (np.rent >= 2000) {
                            return 2;
                        } else {
                            if (np.rent >= 1000) {
                                return 3;
                            } else {
                                if (np.rent >= 500) {
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
