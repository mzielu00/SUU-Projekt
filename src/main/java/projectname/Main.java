package projectname;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import projectname.central.CentralCluster;
import projectname.central.CentralClusterImpl;
import projectname.util.RandomDataProvider;


@SpringBootApplication
public class Main {

  public static void main(String[] args) {
    CentralCluster centralCluster = new CentralClusterImpl();
    RandomDataProvider randomDataProvider = new RandomDataProvider();
    centralCluster.updateData("area1", randomDataProvider.generateSingleRandomData());
    centralCluster.updateData("area2", randomDataProvider.generateSingleRandomData());

    System.out.println(centralCluster.getStringCityData());
  }


}
