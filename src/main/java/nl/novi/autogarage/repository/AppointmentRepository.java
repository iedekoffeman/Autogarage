package nl.novi.autogarage.repository;

import nl.novi.autogarage.model.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {
}
