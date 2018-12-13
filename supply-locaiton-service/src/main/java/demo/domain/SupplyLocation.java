package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
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



@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Document //todo jpa中的entity
@Data
@RequiredArgsConstructor(onConstructor = @_(@PersistenceConstructor))

/*
//todo  0. @NoArgsConstructor, @RequiredArgsConstructor and @AllArgsConstructor叫做"构造器", 也就是说它们是指导如何生成构造函数的
//todo  1. 条件: 针对函数中有final项(object), 目前annotaion起作用(其它两个不知道什么条件). 2.当构造函数被调用的时候, 会在上面加一个@PersistenceConstructor这个annotation
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
    @JsonIgnore  //json进来的时候是两个点, 这个是通过jsoncreator创建的, 所以我们不希望point这个field被json mapping到, 避免错误
    private final Point location; //point是一个object, 他自己的构造函数上就有那个annotation, 就像jpa加上embedded一样,
                                //我们加上final和上面的annotation才能让mongoDB知道这个是个object
    //todo 上面这个Point location一开始写作Point point, 结果repo就不认识它了
    //如果写成了Point point, 就应该在repo里也写成findFirstPointNear之类的

    private String state;
    private String zip;
    private String type;

    public SupplyLocation(){
        this.location=  new Point(0,0);
    }

    @JsonCreator
    public SupplyLocation(@JsonProperty("latitude") int x,@JsonProperty("longitude") int y){
        this.location = new Point(x, y);
    }

    public double getLongitude(){
        return location.getX();
    }

    public double getLatitude(){
        return location.getY();
    }


}
