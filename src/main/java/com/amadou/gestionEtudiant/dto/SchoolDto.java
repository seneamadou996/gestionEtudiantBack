package com.amadou.gestionEtudiant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchoolDto {

    private Integer id;

    private String name;

    private String slogan;

    private AddressDto addressDto;

    private String numberTel;

    private String photo;

    private List<UserDto> userDtos;

}
