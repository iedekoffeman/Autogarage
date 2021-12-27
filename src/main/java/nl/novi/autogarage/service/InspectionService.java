package nl.novi.autogarage.service;

import nl.novi.autogarage.model.Inspection;
import nl.novi.autogarage.repository.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InspectionService {

    @Autowired
    private InspectionRepository inspectionRepository;

    public Iterable<Inspection> getInspections(Date inspectionDate) {

        if(inspectionDate == null) {
            return inspectionRepository.findAll();
        } else {
            return inspectionRepository.findAllByInspectionDate(inspectionDate);
        }

    }

}
