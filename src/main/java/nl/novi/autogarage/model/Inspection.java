package nl.novi.autogarage.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "inspections")
public class Inspection extends Appointment {

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car inspection;

    public Car getInspection() {
        return inspection;
    }

    public void setInspection(Car inspection) {
        this.inspection = inspection;
    }
}
