package nl.novi.autogarage.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity //By using Entity annotation you say this class needs to be a table in our database
@Table(name = "customers")
public class Customer {

    //attributes
    @Id //This is the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Define that this is a Primary key by using the @GeneratedValue annotation.
    private int id;

    private String firstname;
    private String lastname;
    //
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> cars = new ArrayList<>();

    //constructor not needed for now (we leave it empty), because it's not needed to assign a value to attributes directly.

    //setters and getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
