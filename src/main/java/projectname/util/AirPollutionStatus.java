package projectname.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AirPollutionStatus {
  private final int PM10;
  private final int PM2_5;
  private final int PM1;

  @Override
  public String toString(){
    return "PM10:" + PM10 + ",PM2.5:" + PM2_5 + "PM1:" + PM1;
  }
}
