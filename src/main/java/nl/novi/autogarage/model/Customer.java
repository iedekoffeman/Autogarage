package nl.novi.autogarage.model;

public class Customer {

    //attributes
    private String firstname;
    private String lastname;

    //constructor not needed for now (we leave it empty), because it's not needed to assign a value to attributes directly.

    //setters and getters
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
}
