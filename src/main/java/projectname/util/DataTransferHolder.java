package projectname.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DataTransferHolder {
  /**
   * Holder of data that can be transfered from edge cluster to central cluster
   * and then shown to user
   */
  private final AirPollutionStatus airPollutionStatus;
  private final TrafficStatus trafficStatus;

  public String toString(){
    return  "air pollution: " + airPollutionStatus.toString() + ", traffic status: " + trafficStatus.getDescription();
  }
}
