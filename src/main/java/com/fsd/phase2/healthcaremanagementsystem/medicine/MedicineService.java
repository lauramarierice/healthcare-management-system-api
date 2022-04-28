package com.fsd.phase2.healthcaremanagementsystem.medicine;

import com.fsd.phase2.healthcaremanagementsystem.commons.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class MedicineService {

    private final MedicineRepository medicineRepository;

    private final MedicineMapper medicineMapper;

    public List<MedicineDTO> findAllMedicineProducts() {
        return medicineMapper.map(medicineRepository.findAll());
    }
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

    public MedicineDTO addMedicine(MedicineDTO medicineDTO) {
        MedicineEntity medicineEntity = new MedicineEntity();
        medicineEntity.setMedicineName(medicineDTO.getName());
        medicineEntity.setDescription(medicineDTO.getDescription());
        medicineEntity.setPrice(medicineDTO.getPrice());
        medicineEntity.setDiscountedPrice(medicineDTO.getDiscountedPrice());
        medicineEntity.setTreatableDiseases(medicineDTO.getTreatableDiseases());
        medicineEntity.setDescription(medicineDTO.getDescription());
        medicineEntity.setQuantity(medicineDTO.getQuantityAvailable());
        medicineEntity.setExpirationDate(medicineDTO.getExpirationDate());
        return medicineMapper.map(medicineRepository.save(medicineEntity));
    }

    public MedicineDTO updateMedicine(MedicineDTO medicineDTO, Long id) {
        return medicineRepository.findById(id)
                .map(medicine -> {
                    medicine.setBrand(medicineDTO.getBrand());
                    medicine.setPrice(medicineDTO.getPrice());
                    medicine.setQuantity(medicineDTO.getQuantityAvailable());
                    return medicineMapper.map(medicineRepository.save(medicineMapper.map(medicineDTO)));
                }).orElseThrow(() -> new EntityNotFoundException("Medicine not found"));
    }

    @Transactional
    public void deleteMedicine(Long id) {
        try {
            medicineRepository.deleteById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException("Unable to delete medicine");
        }
    }
}
