package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL) //只引入非空field, 用于POJO->JSON
// 将jsonfile中的"runningId" map到runningId这个variable上 //文档中说这个是做序列化(O->J)的时候用的
// 老师, 包括project中做的是反序列化用的, TODO
@Data
@Entity //数据库交互
@Table(name = "LOCATIONS") //TODO 这个不加就是和object同名的table
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

    @Embedded //与object中@Embedable呼应, JPA/Hibernate
              //他的用法就是存入数据库的时候可以把object打包,拍扁, 存入数据库
              //或者把数据库中的吉祥拿出来的时候, 组合成一个object
    @AttributeOverride(name = "bandMake", column = @Column(name = "unit_band_make"))
    UnitInfo unitInfo;

    @Embedded
    @AttributeOverrides({ //todo 这个是定义对应field在数据库colume叫啥, 可以试一下不定义原本叫啥(猜测可能就是本名)
            @AttributeOverride(name = "fmi", column = @Column(name = "medical_fmi"));
            @AttributeOverride(name = "bfr", column = @Column(name = "medical_bfr"));
    })
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

    public Location(){
        this.unitInfo = null;
    }

    public Location(UnitInfo unitInfo){
        this.unitInfo = unitInfo;
    }


    @JsonCreator // json deserialize,
    // The @JsonCreator annotation is used to tune the constructor/factory used in deserialization.
    //It’s very helpful when we need to deserialize some JSON that doesn’t exactly match the target entity we need to get.
    public Location(@JsonProperty("runningId") String runningId){
        this.unitInfo = new UnitInfo(runningId);
    }

    public String getRunningId(){
        return this.unitInfo == null? null :this.unitInfo.getRunningId();
    }
}
