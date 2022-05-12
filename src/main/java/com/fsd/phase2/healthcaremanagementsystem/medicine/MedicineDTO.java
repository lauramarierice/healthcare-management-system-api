package com.fsd.phase2.healthcaremanagementsystem.medicine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class MedicineDTO {

    private Long medicineId;

    private String name;

    private String brand;

    private String description;

    private String treatableDiseases;

    private Double price;

    private Integer quantityAvailable;

    private LocalDateTime expirationDate;

    private Double discountedPrice;

    private String imageUrl;
}
