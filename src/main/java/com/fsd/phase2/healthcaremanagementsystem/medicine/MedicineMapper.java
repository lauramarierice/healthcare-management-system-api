package com.fsd.phase2.healthcaremanagementsystem.medicine;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicineMapper {

    @Mapping(target = "medicineId", source = "medicineId")
    @Mapping(target = "name", source = "medicineName")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "treatableDiseases", source = "treatableDiseases")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "quantityAvailable", source = "quantity")
    @Mapping(target = "expirationDate", source = "expirationDate")
    @Mapping(target = "discountedPrice", source = "discountedPrice")
    MedicineDTO map(MedicineEntity source);

    MedicineEntity map(MedicineDTO source);

    List<MedicineDTO> map(List<MedicineEntity> source);
}