package com.amadou.gestionEtudiant.Validator;

import com.amadou.gestionEtudiant.dto.SchoolDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SchoolValidator {

    public static List<String> validate(SchoolDto schoolDto) {

        List<String> errors = new ArrayList<>();

        if (schoolDto == null) {
            errors.add("Veuillez renseigner le nom");
            errors.add("Veuillez renseigner le numero de telephone");
            errors.addAll(AddressValidator.validate(null));
            return errors;
        }

        if (!StringUtils.hasLength(schoolDto.getName())) {
            errors.add("Veuillez renseigner le nom");
        }

        if (!StringUtils.hasLength(schoolDto.getNumberTel())) {
            errors.add("Veuillez renseigner le numero de telephone");
        }

        errors.addAll(AddressValidator.validate(schoolDto.getAddressDto()));

        return errors;
    }

}
