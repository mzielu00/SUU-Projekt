package projectname.util;

public enum TrafficStatus {

  LIGHT("Light traffic"),
  MODERATE("Moderate traffic"),
  HEAVY("Heavy traffic"),
  STANDSTILL("Standstill traffic"),
  UNKNOWN("Unknown traffic status");

  private final String description;

  TrafficStatus(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

}
