package com.fsd.phase2.healthcaremanagementsystem.logins;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginDTO {

    @NonNull
    private String token;
}
