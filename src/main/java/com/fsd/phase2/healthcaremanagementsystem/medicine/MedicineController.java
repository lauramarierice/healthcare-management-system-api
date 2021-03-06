package com.fsd.phase2.healthcaremanagementsystem.medicine;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @GetMapping(value = "/medicine/{id}")
    public MedicineDTO getMedicineByMedicineId(@PathVariable("id") Long medicineId) {
        return medicineService.getMedicineByMedicineId(medicineId);
    }

    @GetMapping(value = "/medicine/all")
    public List<MedicineDTO> findAllMedicineProducts() {
        return medicineService.findAllMedicineProducts();
    }

    @GetMapping(value = "/medicine", params = {"description"})
    public List<MedicineDTO> findMedicineByDescription(@RequestParam("description") String description) {
        return medicineService.findMedicineByDescription(description);
    }

    @GetMapping(value = "/medicine", params = {"disease"})
    public List<MedicineDTO> findMedicineByDisease(@RequestParam("disease") String disease) {
        return medicineService.findMedicineByDisease(disease);
    }

    @PostMapping(value = "/add/medicine")
    public MedicineDTO addMedicine(@RequestBody MedicineDTO medicineDTO) {
        return medicineService.addMedicine(medicineDTO);
    }

    @PutMapping(value = "/medicine/{id}")
    public MedicineDTO updateMedicine(@RequestBody MedicineDTO medicineDTO, @PathVariable("id") Long id) {
        return medicineService.updateMedicine(medicineDTO, id);
    }

    @DeleteMapping(value = "/delete/medicine/{id}")
    public void deleteMedicine(@PathVariable("id") Long id) {
        medicineService.deleteMedicine(id);
    }
}