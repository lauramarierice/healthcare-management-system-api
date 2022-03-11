package com.fsd.phase2.healthcaremanagementsystem.medicine;

import lombok.*;

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
}
