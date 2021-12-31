package nl.novi.autogarage.service;

import nl.novi.autogarage.dto.DeficiencyRequestDto;
import nl.novi.autogarage.exception.RecordNotFoundException;
import nl.novi.autogarage.model.Deficiency;
import nl.novi.autogarage.repository.DeficiencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeficiencyService {

    @Autowired
    private DeficiencyRepository deficiencyRepository;


    public List<Deficiency> getDeficiencies() {

            return deficiencyRepository.findAll();

    }
    public Deficiency getDeficiency(int id) {
        Optional<Deficiency> optionalDeficiency = deficiencyRepository.findById(id);

        if(optionalDeficiency.isPresent()) {
            return optionalDeficiency.get();
        } else {
            throw new RecordNotFoundException("A Deficiency with ID " + id + " does not exist.");
        }
    }
    public void deleteDeficiency(int id) {
        deficiencyRepository.deleteById(id);
    }
    public int addDeficiency(DeficiencyRequestDto deficiencyRequestDto) {

        Deficiency deficiency = new Deficiency();
        deficiency.setDescription(deficiencyRequestDto.getDescription());

        Deficiency newDeficiency = deficiencyRepository.save(deficiency);
        return newDeficiency.getId();

    }
    public void updateDeficiency(int id, Deficiency deficiency) {
        Deficiency existingDeficiency = deficiencyRepository.findById(id).orElse(null);

        if (!deficiency.getDescription().isEmpty()) {
            existingDeficiency.setDescription(deficiency.getDescription());
        }
        deficiencyRepository.save(existingDeficiency);
    }
    public void partialUpdateDeficiency(int id, Deficiency deficiency) {
        Deficiency existingDeficiency = deficiencyRepository.findById(id).orElse(null);

        if (!(deficiency.getDescription() == null) && !deficiency.getDescription().isEmpty()) {
            existingDeficiency.setDescription(deficiency.getDescription());
        }

        deficiencyRepository.save(existingDeficiency);
    }


}
