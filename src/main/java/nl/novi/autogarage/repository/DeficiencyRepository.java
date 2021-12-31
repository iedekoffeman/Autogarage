package nl.novi.autogarage.repository;

import nl.novi.autogarage.model.Deficiency;
import nl.novi.autogarage.model.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeficiencyRepository extends JpaRepository<Deficiency, Integer> {


}
