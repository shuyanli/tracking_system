package toy.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "CUSTOMER")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private int point;


    protected Customer(){}

    //@JsonCreator //用作嵌套object的时候, 逆序列化时用传进来的json的某一个field作为嵌套object的constructor
    public Customer(String firstName, String lastName, int point) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "Customer: " + firstName + " " + lastName + ": with Id:" + id;
    }
}
