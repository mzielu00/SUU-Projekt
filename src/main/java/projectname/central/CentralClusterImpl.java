package projectname.central;

import lombok.NoArgsConstructor;
import projectname.util.DataTransferHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor
public class CentralClusterImpl implements CentralCluster{

  private final HashMap<String, DataTransferHolder> cityData = new HashMap<>();

  @Override
  public HashMap<String, DataTransferHolder> getCityData() {
    return cityData;
  }

  @Override
  public Set<String> getAreas() {
    return cityData.keySet();
  }

  @Override
  public DataTransferHolder getSingleAreaData(String place) {
    return cityData.getOrDefault(place, null);
  }

  @Override
  public void updateData(String place, DataTransferHolder data) {
    cityData.put(place, data);
  }

  @Override
  public String getStringCityData() {
    String stringData = "";
    for (Map.Entry<String, DataTransferHolder> entry : cityData.entrySet()) {
      stringData += "area: " + entry.getKey() + ", data: " + entry.getValue().toString() + ";\n";
    }
    return stringData;
  }
}
