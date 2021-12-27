package nl.novi.autogarage.service;

import nl.novi.autogarage.model.Repair;
import nl.novi.autogarage.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RepairService {

    @Autowired
    private RepairRepository repairRepository;

    public Iterable<Repair> getRepairs(LocalDate inspectionDate) {

        if(inspectionDate == null) {
            return repairRepository.findAll();
        } else {
            return repairRepository.findAllByRepairDate(inspectionDate);
        }

    }
}
