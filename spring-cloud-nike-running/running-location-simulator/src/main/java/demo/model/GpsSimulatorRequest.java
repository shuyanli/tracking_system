package demo.model;


import lombok.Data;

@Data
public class GpsSimulatorRequest {
    private String runningId;
    private double speed;
    private boolean move = true;
    private boolean exportPositionsToMessaging = true; //todo->管控是否向distribution发信息
    private Integer reportInternal = 500;
    private RunnerStatus runnerStatus = RunnerStatus.NONE;
    private String polyline;
    private MedicalInfo medicalInfo;


}
