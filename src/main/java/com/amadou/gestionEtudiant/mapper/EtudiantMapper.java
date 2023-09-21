package com.amadou.gestionEtudiant.mapper;

import com.amadou.gestionEtudiant.dto.EtudiantDto;
import com.amadou.gestionEtudiant.model.Etudiant;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EtudiantMapper {

    private final ModelMapper modelMapper;

    public EtudiantDto fromEntity(Etudiant etudiant) {
        if (etudiant == null) {
            return null;
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        EtudiantDto etudiantDto;
        etudiantDto = modelMapper.map(etudiant, EtudiantDto.class);
        return etudiantDto;

    }

    public Etudiant toEntity(EtudiantDto etudiantDto) {
        if (etudiantDto == null) {
            return null;
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Etudiant etudiant;
        etudiant = modelMapper.map(etudiantDto, Etudiant.class);
        return etudiant;

    }

}
