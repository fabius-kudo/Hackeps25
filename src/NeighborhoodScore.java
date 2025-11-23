public class NeighborhoodScore {
    String name;
    int safety;
    int publicTransport;
    int liveliness;
    int nature;
    int rent;
    double totalScore;

    public NeighborhoodScore(String name, int safety, int publicTransport,
                             int liveliness, int nature, int rent) {
        this.name = name;
        this.safety = safety;
        this.publicTransport = publicTransport;
        this.liveliness = liveliness;
        this.nature = nature;
        this.rent = rent;
    }

    public void calculateScore(UserPreferences prefs) {
        this.totalScore = (safety * prefs.safetyWeight) +
                (publicTransport * prefs.transportWeight) +
                (liveliness * prefs.livelinessWeight) +
                (nature * prefs.natureWeight) +
                (rent * prefs.rentWeight);
    }
}
