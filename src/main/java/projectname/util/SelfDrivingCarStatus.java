package projectname.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SelfDrivingCarStatus {
    private int conditions;

    public String checkConditions() {
        if (conditions == 0) {
            return "Dangerous surroundings: There are vehicles, pedestrians, or cyclists in close proximity or approaching rapidly. Take appropriate action.";
        }
        else if (conditions == 1) {
            return "Poor road conditions: There are potholes or the road is slippery. Take appropriate action.";
        }
        else if (conditions == 2) {
            return "Challenging weather conditions: It is nighttime, foggy, or raining. Take appropriate action.";
        }
        else return "Good conditions";
    }
}
