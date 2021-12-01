package nl.novi.autogarage.model;


import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    //attributes
    @Id //This is the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Define that this is a Primary key by using the @GeneratedValue annotation.
    private int id;

    private String licenseplate;

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
}
