package demo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;


@Data
@Entity
public class Location {

    public enum GpsStatus{
        EXCELELENT, OK, UNRELIABLE, BAD, NOFIX, UNKNOWN;
    }

    public enum RunnerMovementType{
        IN_MOTION, STOPPED;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    UnitInfo unitInfo;
    MedicalInfo medicalInfo;

    private double latitude;
    private double longitude;
    private String heading; // lat, long (googlemap->) LEG
    private double gpsSpeed;
    private GpsStatus gpsStatus;
    private double odometer;
    private RunnerMovementType runnerMovementType;
    private double totalRunningTime;
    private double totalIdleTime;
    private double totalCalorieBurnt;
    private String address;
    private Date timestemp = new Date();
    private String gearPrivider;
    private String serviceType;

}
