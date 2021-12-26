package nl.novi.autogarage.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "inspections")
public class Inspection extends Appointment {

    private boolean customerApproved;

    public boolean isCustomerApproved() {
        return customerApproved;
    }

    public void setCustomerApproved(boolean customerApproved) {
        this.customerApproved = customerApproved;
    }
}
