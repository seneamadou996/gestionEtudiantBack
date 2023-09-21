package com.amadou.gestionEtudiant.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleUserFormDto {

    private String username;
    private String rolename;

}
