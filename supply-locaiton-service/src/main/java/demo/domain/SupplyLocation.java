package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

//import javax.persistence.GeneratedValue;
//import javax.persistence.Id; //todo 这两个id有什么不一样
//todo 回答: annotaion用于处理nosql, persistence用于jpa(如之前的h2)


@Document //todo jpa中的entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@RequiredArgsConstructor(onConstructor = @_(@PersistenceConstructor)) 
/*
//todo  0. @NoArgsConstructor, @RequiredArgsConstructor and @AllArgsConstructor叫做"构造器", 也就是说它们是指导如何生成构造函数的
//todo  1. 条件: 函数中有final项, 目前annotaion起作用(其它两个不知道什么条件). 2.当构造函数被调用的时候, 会在上面加一个@PersistenceConstructor这个annotation
//todo  3. PersistenceConstructor的作用是声明一个构造函数时, 传入的值是从数据库中取出的数据\

也就是说, lombok会给我们弄出这么个东西(我们看不见)
@PersistenceConstructor
  public SupplyLocation(xx, xx, xx, xx...) {
    // …
  }

*/
public class SupplyLocation {

    @Id
    private String id;

    private String address1;

    private String city;

    //对应上面的spring data geo location. 是mongodb自带的对于地理信息的index, 包含x和y, 通过point就可以
    //加速对经纬度的查询的速度
    @GeoSpatialIndexed
    private final Point point;


    private String state;
    private String zip;
    private String type;

    public SupplyLocation(){
        this.point=  new Point(0,0);
    }

    @JsonCreator
    public SupplyLocation(@JsonProperty("latitude") int x,@JsonProperty("longitude") int y){
        this.point = new Point(x, y);
    }

    public double getLongitude(){
        return point.getX();
    }

    public double getLatitude(){
        return point.getY();
    }


}
