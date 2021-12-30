package nl.novi.autogarage.service;

import nl.novi.autogarage.dto.InspectionRequestDto;
import nl.novi.autogarage.exception.RecordNotFoundException;
import nl.novi.autogarage.model.Inspection;
import nl.novi.autogarage.repository.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class InspectionService {

    @Autowired
    private InspectionRepository inspectionRepository;

    public Iterable<Inspection> getInspections(LocalDate appointmentDate) {

        if(appointmentDate == null) {
            return inspectionRepository.findAll();
        } else {
            return inspectionRepository.findAllByAppointmentDate(appointmentDate);
        }

    }

    public Inspection getInspection(int id) {
        Optional<Inspection> optionalInspection = inspectionRepository.findById(id);

        if(optionalInspection.isPresent()) {
            return optionalInspection.get();
        } else {
            throw new RecordNotFoundException("An Inspection with ID " + id + " does not exist.");
        }
    }

    public void deleteInspection(int id) {
        inspectionRepository.deleteById(id);
    }

    public int addInspection(InspectionRequestDto inspectionRequestDto) {

            Inspection inspection = new Inspection();
            inspection.setAppointmentDate(inspectionRequestDto.getAppointmentDate());
            inspection.setAppointmentStatus(inspectionRequestDto.getAppointmentStatus());
            Inspection newInspection = inspectionRepository.save(inspection);
            return newInspection.getId();
    }

    public void updateInspection(int id, InspectionRequestDto inspectionRequestDto) {

        Optional<Inspection> optionalInspection = inspectionRepository.findById(id);

        if(optionalInspection.isPresent()) {

            Inspection inspection = optionalInspection.get();
            inspection.setAppointmentDate(inspectionRequestDto.getAppointmentDate());
            inspection.setAppointmentStatus(inspectionRequestDto.getAppointmentStatus());
            inspectionRepository.save(inspection);

        } else {

            throw new RecordNotFoundException("An inspection with ID " + id + " does not exist.");
        }


    }
    public void partialUpdateInspection(int id, InspectionRequestDto inspectionRequestDto) {

        Optional<Inspection> optionalInspection = inspectionRepository.findById(id);

        if(optionalInspection.isPresent()) {

            Inspection inspection = optionalInspection.get();

            if(inspectionRequestDto.getAppointmentDate() != null) {

                inspection.setAppointmentDate(inspectionRequestDto.getAppointmentDate());

            }

            if(inspectionRequestDto.getAppointmentStatus() != null) {

                inspection.setAppointmentStatus(inspectionRequestDto.getAppointmentStatus());

            }

            inspectionRepository.save(inspection);

        } else {

            throw new RecordNotFoundException("An inspection with ID " + id + " does not exist.");
        }
    }



}
