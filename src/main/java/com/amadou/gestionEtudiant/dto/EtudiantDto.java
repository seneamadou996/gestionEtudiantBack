package com.amadou.gestionEtudiant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantDto {

    private Integer id;

    private String firstname;

    private String lastname;

    private Date birthday;

    private AddressDto addressDto;

    private String numberTel;

    private String photo;

    private Integer idSchool;

}
