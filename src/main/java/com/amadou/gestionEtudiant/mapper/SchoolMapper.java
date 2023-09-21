package com.amadou.gestionEtudiant.mapper;

import com.amadou.gestionEtudiant.dto.SchoolDto;
import com.amadou.gestionEtudiant.model.School;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolMapper {

    private final ModelMapper modelMapper;

    public SchoolDto fromEntity(School school) {
        if (school == null) {
            return null;
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        SchoolDto schoolDto;
        schoolDto = modelMapper.map(school, SchoolDto.class);
        return schoolDto;

    }

    public School toEntity(SchoolDto schoolDto) {
        if (schoolDto == null) {
            return null;
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        School school;
        school = modelMapper.map(schoolDto, School.class);
        return school;

    }

}
