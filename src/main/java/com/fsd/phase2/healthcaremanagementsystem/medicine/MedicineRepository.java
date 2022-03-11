package com.fsd.phase2.healthcaremanagementsystem.medicine;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicineRepository extends JpaRepository<MedicineEntity, Long> {

    Optional<MedicineEntity> findById(Long id);

    void deleteById(Long id);

    List<MedicineEntity> findByDescriptionContaining(String description);

    List<MedicineEntity> findByTreatableDiseases(String treatableDisease);
}
