import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

class NeighborhoodLiveliness {
    public String name;
    public int liveliness;
}

public class LivelinessFilter {
    public int getScore(String neighborhoodName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<NeighborhoodLiveliness> livelinessList = mapper.readValue(
                    new File("liveliness.json"),
                    new TypeReference<List<NeighborhoodLiveliness>>() {}
            );

            for (NeighborhoodLiveliness np : livelinessList) {
                if (np.name.equals(neighborhoodName)) {
                    if (np.liveliness <= 5) {
                        return 1;
                    } else {
                        if (np.liveliness <= 10) {
                            return 2;
                        } else {
                            if (np.liveliness <= 20) {
                                return 3;
                            } else {
                                if (np.liveliness <= 35) {
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
            System.err.println("Error reading liveliness data: " + e.getMessage());
            return 0;
        }
    }
}
