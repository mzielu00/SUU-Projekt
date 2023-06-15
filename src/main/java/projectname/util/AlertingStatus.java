package projectname.util;

public enum AlertingStatus {
    FIRE_ALARM("Fire alarm triggered. Evacuate the area immediately!"),
    EARTHQUAKE_ALERT("Earthquake alert. Take cover and seek shelter!"),
    FLOOD_ALERT("Flood alert. Move to higher ground!"),
    NO_ALERT("No alert currently.");

    private final String message;

    AlertingStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}