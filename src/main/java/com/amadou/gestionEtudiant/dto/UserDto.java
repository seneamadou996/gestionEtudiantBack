package com.amadou.gestionEtudiant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String numberTel;

    private String username;

    private String password;

    private String photo;

    private AddressDto addessDto;

    private SchoolDto schoolDto;

    private List<RoleDto> rolesDtos = new ArrayList<>();

}
