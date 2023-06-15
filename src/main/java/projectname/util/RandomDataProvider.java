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

  public DataTransferHolder generateSingleRandomData() {
    return new DataTransferHolder(generateRandomAirPollutionStatus(), generateRandomTrafficStatus());
  }

}
