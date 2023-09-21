package com.amadou.gestionEtudiant.Validator;

import com.amadou.gestionEtudiant.dto.AddressDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AddressValidator {

    public static List<String> validate(AddressDto addressDto) {
        List<String> errors = new ArrayList<>();

        if (addressDto == null){
            errors.add("Veuillez renseigner l'adresse");
            errors.add("Veuillez renseigner la ville");
            errors.add("Veuillez renseigner le pays");
            return errors;
        }

        if (!StringUtils.hasLength(addressDto.getAddress())){
            errors.add("Veuillez renseigner l'adresse 1");
        }
        if (!StringUtils.hasLength(addressDto.getCity())){
            errors.add("Veuillez renseigner la ville");
        }
        if (!StringUtils.hasLength(addressDto.getCountry())){
            errors.add("Veuillez renseigner le pays");
        }

        return errors;

    }

}
