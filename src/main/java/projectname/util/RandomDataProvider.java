package projectname.util;

import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor
public class RandomDataProvider {

  private static final Random random = new Random();

  private static TrafficStatus generateRandomTrafficStatus() {
    TrafficStatus[] statusValues = TrafficStatus.values();
    int index = random.nextInt(statusValues.length);
    return statusValues[index];
  }

  private static AirPollutionStatus generateRandomAirPollutionStatus() {
    int PM10_bound = 50;
    int PM2_5_bound = 25;
    int PM1_bound = 10;
    return new AirPollutionStatus(random.nextInt(PM10_bound), random.nextInt(PM2_5_bound), random.nextInt(PM1_bound));
  }

  private static ParkingStatus generateRandomParkingStatus() {
    int totalSpaces = random.nextInt(150);
    int availableSpaces = random.nextInt(totalSpaces + 1);
    return new ParkingStatus(availableSpaces, totalSpaces);
  }

  private static AlertingStatus generateRandomAlertType() {
    AlertingStatus[] alertTypes = AlertingStatus.values();
    int index = random.nextInt(alertTypes.length);
    return alertTypes[index];
  }

  private static SelfDrivingCarStatus generateDrivingConditionStatus() {
    return new SelfDrivingCarStatus(random.nextInt(4));
  }

  public DataTransferHolder generateSingleRandomData() {
    return new DataTransferHolder(generateRandomAirPollutionStatus(), generateRandomTrafficStatus(), generateRandomParkingStatus(), generateRandomAlertType(), generateDrivingConditionStatus());
  }

}
