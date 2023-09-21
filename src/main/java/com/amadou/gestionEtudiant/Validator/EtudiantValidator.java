package com.amadou.gestionEtudiant.Validator;

import com.amadou.gestionEtudiant.dto.EtudiantDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EtudiantValidator {

    public static List<String> validate(EtudiantDto etudiantDto) {

        List<String> errors = new ArrayList<>();

        if (etudiantDto == null) {
            errors.add("Veuillez renseigner le prenom");
            errors.add("Veuillez renseigner le nom");
            errors.add("Veuillez renseigner le numero de telephone");
            errors.add("Veuillez renseigner la date de naissance de l'etudiant");
            errors.addAll(AddressValidator.validate(null));
            return errors;
        }

        if (!StringUtils.hasLength(etudiantDto.getFirstname())) {
            errors.add("Veuillez renseigner le prenom");
        }

        if (!StringUtils.hasLength(etudiantDto.getLastname())) {
            errors.add("Veuillez renseigner le nom");
        }

        if (!StringUtils.hasLength(etudiantDto.getNumberTel())) {
            errors.add("Veuillez renseigner le numero de telephone");
        }

        errors.addAll(AddressValidator.validate(etudiantDto.getAddressDto()));

        return errors;
    }

}
