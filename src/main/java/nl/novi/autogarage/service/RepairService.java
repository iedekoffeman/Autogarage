package nl.novi.autogarage.service;

import nl.novi.autogarage.dto.RepairRequestDto;
import nl.novi.autogarage.exception.RecordNotFoundException;
import nl.novi.autogarage.model.Repair;
import nl.novi.autogarage.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RepairService {

    @Autowired
    private RepairRepository repairRepository;

    public Repair getRepair(int id) {
        Optional<Repair> optionalRepair = repairRepository.findById(id);

        if(optionalRepair.isPresent()) {
            return optionalRepair.get();
        } else {
            throw new RecordNotFoundException("A repair with ID " + id + " does not exist.");
        }
    }

    public Iterable<Repair> getAllRepairs(LocalDate appointmentDate) {

        if(appointmentDate == null) {

            return repairRepository.findAll();

        } else {

            Iterable<Repair> repairs =  repairRepository.findAllByAppointmentDate(appointmentDate);

            int counter = 0;
            for(Object i : repairs) {
                counter++;
            }

            if(counter==0) {

                throw new RecordNotFoundException("There are no repairs found for the given date");

            } else {

                return repairs;
            }
        }

    }

    public void deleteRepair(int id) {
        repairRepository.deleteById(id);
    }

    public int createRepair(RepairRequestDto repairRequestDto) {

        Repair repair = new Repair();
        repair.setAppointmentDate(repairRequestDto.getAppointmentDate());
        repair.setAppointmentStatus(repairRequestDto.getAppointmentStatus());
        Repair newRepair = repairRepository.save(repair);
        return newRepair.getId();
    }

    public void updateRepair(int id, RepairRequestDto repairRequestDto) {

        Optional<Repair> optionalRepair = repairRepository.findById(id);

        if(optionalRepair.isPresent()) {

            Repair repair = optionalRepair.get();
            repair.setAppointmentDate(repairRequestDto.getAppointmentDate());
            repair.setAppointmentStatus(repairRequestDto.getAppointmentStatus());
            repairRepository.save(repair);

        } else {

            throw new RecordNotFoundException("A repair with ID " + id + " does not exist.");
        }


    }
    public void partialUpdateRepair(int id, RepairRequestDto repairRequestDto) {

        Optional<Repair> optionalRepair = repairRepository.findById(id);

        if(optionalRepair.isPresent()) {

            Repair repair = optionalRepair.get();

            if(repairRequestDto.getAppointmentDate() != null) {

                repair.setAppointmentDate(repairRequestDto.getAppointmentDate());

            }

            if(repairRequestDto.getAppointmentStatus() != null) {

                repair.setAppointmentStatus(repairRequestDto.getAppointmentStatus());

            }

            repairRepository.save(repair);

        } else {

            throw new RecordNotFoundException("A repair with ID " + id + " does not exist.");
        }
    }

}
