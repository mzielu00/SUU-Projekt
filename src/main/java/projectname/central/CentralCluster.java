package projectname.central;


import projectname.util.DataTransferHolder;

import java.util.HashMap;
import java.util.Set;

public interface CentralCluster {
  /**
   * Central Cluster job is to provide generalized data from edge cluster for user
   * It will provide information about air pollution and traffic
   */

  /**
   * Gives all data about city that is currently available for central cluster
   */
  HashMap<String, DataTransferHolder> getCityData();

  /**
   * Gives list of places about which central cluster holds informations
   * @return set of strings that identifies areas in city
   */
  Set<String> getAreas();

  /**
   * Provides data about air poluttion and traffic status for choosen area.
   * @param place string that identifies area
   */
  DataTransferHolder getSingleAreaData(String place);

  /**
   * Adds new data about city or updates all data
   */
  void updateData(String place, DataTransferHolder data);

  /**
   * Provides String representation of hashMap which keeps city data
   */
  String getStringCityData();



}
