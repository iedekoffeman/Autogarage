package nl.novi.autogarage.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "appointments")
public class Appointment {

    private Date date;

}
