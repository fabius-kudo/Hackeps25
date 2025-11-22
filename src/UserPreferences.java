public class UserPreferences {
    int safetyWeight;
    int transportWeight;
    int livelinessWeight;
    int natureWeight;
    int rentWeight;

    public UserPreferences(int safety, int transport, int liveliness,
                           int nature, int rent) {
        this.safetyWeight = safety;
        this.transportWeight = transport;
        this.livelinessWeight = liveliness;
        this.natureWeight = nature;
        this.rentWeight = rent;
    }
}
