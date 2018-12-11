package toy.domain;

import lombok.Data;

import javax.persistence.*;

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
