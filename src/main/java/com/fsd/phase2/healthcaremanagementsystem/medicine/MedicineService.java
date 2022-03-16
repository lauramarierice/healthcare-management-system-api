package com.fsd.phase2.healthcaremanagementsystem.medicine;

import com.fsd.phase2.healthcaremanagementsystem.commons.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
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

    public MedicineDTO updateMedicine(@RequestBody MedicineDTO medicineDTO, Long id) {
        return medicineRepository.findById(id)
                .map(medicine -> {
                    medicine.setBrand(medicineDTO.getBrand());
                    medicine.setPrice(medicineDTO.getPrice());
                    medicine.setQuantity(medicineDTO.getQuantityAvailable());
                    return medicineMapper.map(medicineRepository.save(medicineMapper.map(medicineDTO)));
                })
                .orElseThrow(EntityNotFoundException::new);
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
