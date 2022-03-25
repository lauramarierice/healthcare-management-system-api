package com.fsd.phase2.healthcaremanagementsystem.medicine;

import com.fsd.phase2.healthcaremanagementsystem.commons.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class MedicineService {

    private final MedicineRepository medicineRepository;

    private final MedicineMapper medicineMapper;

    public MedicineDTO getMedicineByMedicineId(Long medicineId) {
        return medicineMapper.map(medicineRepository.findById(medicineId)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("Medicine not found.");
                }));
    }

    public List<MedicineDTO> findMedicineByDescription(String description) {
        return medicineMapper.map(medicineRepository.findByDescriptionContaining(description));
    }

    public List<MedicineDTO> findMedicineByDisease(String treatableDisease) {
        return medicineMapper.map(medicineRepository.findByTreatableDiseases(treatableDisease));
    }

    public MedicineDTO addMedicine(@RequestBody MedicineDTO medicineDTO) {
        return medicineMapper.map(medicineRepository.save(medicineMapper.map(medicineDTO)));
    }

    public ResponseEntity<?> updateMedicine(@RequestBody MedicineDTO medicineDTO, Long id) {
        MedicineDTO medicineToUpdate = medicineRepository.findById(id)
                .map(medicine -> {
                    medicine.setBrand(medicineDTO.getBrand());
                    medicine.setPrice(medicineDTO.getPrice());
                    medicine.setQuantity(medicineDTO.getQuantityAvailable());
                    return medicineMapper.map(medicineRepository.save(medicineMapper.map(medicineDTO)));
                }).orElse(null);

        if(medicineToUpdate == null) {
            return new ResponseEntity<>("Medicine not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(medicineToUpdate, HttpStatus.OK);
    }

    @Transactional
    public void deleteMedicine(Long id) {
        try {
            medicineRepository.deleteById(id);
        } catch(Exception e) {
            throw new EntityNotFoundException();
        }
    }
}
