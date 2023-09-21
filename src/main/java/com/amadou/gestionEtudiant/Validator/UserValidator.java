package com.amadou.gestionEtudiant.Validator;

import com.amadou.gestionEtudiant.dto.UserDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    public static List<String> validate(UserDto userDto){

        List<String> errors = new ArrayList<>();

        if (userDto == null){
            errors.add("Veuillez renseigner le nom de l'utilisateur");
            errors.add("Veuillez renseigner le prenom de l'utilisateur");
            errors.add("Veuillez renseigner l'email de l'utilisateur");
            errors.add("Veuillez renseigner le nom d'utilisateur");
            errors.add("Veuillez renseigner le mot de passe de l'utilisateur");
            errors.add("Veuillez renseigner l'adresse de l'utilisateur");
            errors.addAll(AddressValidator.validate(null));
            return errors;
        }

        if (!StringUtils.hasLength(userDto.getLastName())){
            errors.add("Veuillez renseigner le nom de l'utilisateur");
        }

        if (!StringUtils.hasLength(userDto.getFirstName())){
            errors.add("Veuillez renseigner le prenom de l'utilisateur");
        }

        if (!StringUtils.hasLength(userDto.getEmail())){
            errors.add("Veuillez renseigner l'email de l'utilisateur");
        }

        if (!StringUtils.hasLength(userDto.getUsername())){
            errors.add("Veuillez renseigner le nom d'utilisateur");
        }

        if (!StringUtils.hasLength(userDto.getPassword())){
            errors.add("Veuillez renseigner le mot de passe de l'utilisateur");
        }

        errors.addAll(AddressValidator.validate(userDto.getAddessDto()));

        return errors;

    }

}
