package com.fsd.phase2.healthcaremanagementsystem.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum FilterByTypeEnum {
    WEEKS("weeks"),
    DAYS("days"),
    MONTHS("months"),
    YEARS("years");

    @Getter
    private String filterByType;
}
