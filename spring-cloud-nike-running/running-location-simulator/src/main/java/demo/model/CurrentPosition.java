package demo.model;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CurrentPosition { //一个人的当前地理信息
    private String runningId;
    private RunnerStatus runnerStatus = RunnerStatus.NONE;
    private Point location;
    private double speed;
    private double heading;
    private MedicalInfo medicalInfo;
}
