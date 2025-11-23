import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilterManager {
    //get list of neighborhoods from api
    public List<String> loadNeighborhoods() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<String> neighborhoods = mapper.readValue(
                    new File("Python2/JSON/la_neighbourhoods_clean.json"),
                    List.class
            );
            return neighborhoods;
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public NeighborhoodScore getNeighborhoodData (UserPreferences userPrefs) {
        List<String> neighborhoods = loadNeighborhoods();

        SafetyFilter safetyFilter = new SafetyFilter();
        PublicTransportFilter publicTransportFilter = new PublicTransportFilter();
        LivelinessFilter livelinessFilter = new LivelinessFilter();
        NatureFilter natureFilter = new NatureFilter();
        RentFilter rentFilter = new RentFilter();

        NeighborhoodScore bestNeighborhood = null;
        double highestScore = -1;

        for (int i=0; i < neighborhoods.size(); i++) {
            String name = neighborhoods.get(i);
            int safety = safetyFilter.getScore(name);
            int publicTransport = publicTransportFilter.getScore(name);
            int liveliness = livelinessFilter.getScore(name);
            int nature = natureFilter.getScore(name);
            int rent = rentFilter.getScore(name);

            NeighborhoodScore current = new NeighborhoodScore(
                    name, safety, publicTransport, liveliness, nature, rent
            );
            current.calculateScore(userPrefs);

            if (current.totalScore > highestScore) {
                highestScore = current.totalScore;
                bestNeighborhood = current;
            }
        }
        return bestNeighborhood;
    }
}
