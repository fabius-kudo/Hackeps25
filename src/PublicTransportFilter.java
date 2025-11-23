import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

class NeighborhoodTransport {
    public String name;
    public int transport;
}

public class PublicTransportFilter {
    public int getScore(String neighborhoodName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<NeighborhoodTransport> transportList = mapper.readValue(
                    new File("Python2/JSON/publicTransport.json"),
                    new TypeReference<List<NeighborhoodTransport>>() {}
            );

            for (NeighborhoodTransport nt : transportList) {
                if (nt.name.equals(neighborhoodName)) {
                    if (nt.transport <= 1) {
                        return 1;
                    } else if (nt.transport <= 2) {
                        return 2;
                    } else if (nt.transport <= 3) {
                        return 3;
                    } else if (nt.transport <= 4) {
                        return 4;
                    } else {
                        return 5;
                    }
                }
            }
            return 0;  // Neighborhood not found

        } catch (IOException e) {
            System.err.println("Error reading transport data: " + e.getMessage());
            return 0;
        }
    }
}