package com.amadou.gestionEtudiant.mapper;

import com.amadou.gestionEtudiant.dto.RoleDto;
import com.amadou.gestionEtudiant.model.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleMapper {

    private final ModelMapper modelMapper;

    public RoleDto fromEntity(Role role) {
        if (role == null) {
            return null;
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        RoleDto roleDto;
        roleDto = modelMapper.map(role, RoleDto.class);
        return roleDto;

    }

    public Role toEntity(RoleDto roleDto) {
        if (roleDto == null) {
            return null;
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Role role;
        role = modelMapper.map(roleDto, Role.class);
        return role;

    }

}
