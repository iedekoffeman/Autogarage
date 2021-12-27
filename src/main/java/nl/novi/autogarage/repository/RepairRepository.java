package nl.novi.autogarage.repository;

import nl.novi.autogarage.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Integer> {

    Iterable<Repair> findAllByRepairDate(Date repairDate);

}
