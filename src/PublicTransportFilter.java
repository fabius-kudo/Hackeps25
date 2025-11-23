import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

class NeighborhoodTransport {
    public String name;
    public int transport;
}

public class TransportFilter {
    public int getScore(String neighborhoodName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<NeighborhoodTransport> parksList = mapper.readValue(
                    new File("transport.json"),
                    new TypeReference<List<NeighborhoodTransport>>() {}
            );

            for (NeighborhoodTransport np : transportList) {
                if (np.name.equals(neighborhoodName)) {
                    if (np.transport <= 1) {
                        return 1;
                    } else {
                        if (np.transport <= 2) {
                            return 2;
                        } else {
                            if (np.transport <= 3) {
                                return 3;
                            } else {
                                if (np.transport <= 4) {
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
            System.err.println("Error reading transport data: " + e.getMessage());
            return 0;
        }
    }
}
