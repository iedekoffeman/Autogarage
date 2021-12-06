package nl.novi.autogarage.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    //attributes
    @Id //This is the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Define that this is a Primary key by using the @GeneratedValue annotation.
    private int id;

    private String licenseplate;

    @JsonIgnoreProperties("cars")
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicenseplate() {
        return licenseplate;
    }

    public void setLicenseplate(String licenseplate) {
        this.licenseplate = licenseplate;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }
}
