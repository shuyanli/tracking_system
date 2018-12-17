package demo.model;

import lombok.Data;

@Data
public class Leg {
    private int id;
    private Point startPoint;
    private Point endPoint;
    private double heading;
    private double length;

    public Leg(){}
}
