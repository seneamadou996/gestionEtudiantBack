package com.amadou.gestionEtudiant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeUserPasswordDto {

    private Integer id;

    private String password;

    private String confirmPassword;

}
