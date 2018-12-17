package demo.model;

import lombok.Data;

@Data
public class PositionInfo { //todo 这个和currentPositoin的关系?
    private String runningId;
    private Point position;
    private RunnerStatus runnerStatus = RunnerStatus.NONE;

    private Leg leg;

    private double distanceFromStart;

    private double speed;

    public PositionInfo(){}

}
