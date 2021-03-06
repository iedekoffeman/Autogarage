package nl.novi.autogarage.repository;

import nl.novi.autogarage.model.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InspectionRepository extends JpaRepository<Inspection, Integer> {

    Iterable<Inspection> findAllByAppointmentDate(LocalDate appointmentDate);

}
