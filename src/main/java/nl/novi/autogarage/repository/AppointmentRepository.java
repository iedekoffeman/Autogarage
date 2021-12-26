package nl.novi.autogarage.repository;

import nl.novi.autogarage.model.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDate;

@NoRepositoryBean
public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

    Iterable<Appointment> findAllByAppointmentDate(LocalDate appointmentDate);

}
