package nl.novi.autogarage.service;

import nl.novi.autogarage.dto.CustomerRequestDto;
import nl.novi.autogarage.dto.InspectionRequestDto;
import nl.novi.autogarage.exception.BadRequestException;
import nl.novi.autogarage.exception.RecordNotFoundException;
import nl.novi.autogarage.model.AppointmentStatus;
import nl.novi.autogarage.model.Car;
import nl.novi.autogarage.model.Customer;
import nl.novi.autogarage.model.Inspection;
import nl.novi.autogarage.repository.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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

    public void updateInspection(int id, Inspection inspection) {
        Inspection existingInspection = inspectionRepository.findById(id).orElse(null);

        /*
        if (!inspection.getAppointmentDate().is) {
            existingCustomer.setFirstname(customer.getFirstname());
        }
        if (!customer.getLastname().isEmpty()) {
            existingCustomer.setLastname(customer.getLastname());
        }
        */
        inspectionRepository.save(existingInspection);
    }
    public void partialUpdateInspection(int id, Inspection inspection) {
        Inspection existingInspection = inspectionRepository.findById(id).orElse(null);
    /*
        if (!(customer.getFirstname() == null) && !customer.getFirstname().isEmpty()) {
            existingCustomer.setFirstname(customer.getFirstname());
        }
        if (!(customer.getLastname() == null) && !customer.getLastname().isEmpty()) {
            existingCustomer.setLastname(customer.getLastname());
        }
    */
        inspectionRepository.save(existingInspection);
    }



}
