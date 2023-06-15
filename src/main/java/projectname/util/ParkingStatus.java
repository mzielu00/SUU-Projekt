package projectname.util;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public class ParkingStatus {
    private int availableSpaces;
    private int totalSpaces;

    public String getAvailablePercentage() {
        if (totalSpaces == 0) {
            return "0.0%";
        }
        double percentage =  (double) availableSpaces / totalSpaces * 100.0;
        return String.format("%.2f%%", percentage);
    }

    @Override
    public String toString() {
        return "Total Spaces: " + totalSpaces + ", available Spaces: " + availableSpaces
                + ", percentage of available spaces: " + getAvailablePercentage();
    }
}
