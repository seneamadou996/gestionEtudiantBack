package com.amadou.gestionEtudiant.mapper;

import com.amadou.gestionEtudiant.dto.UserDto;
import com.amadou.gestionEtudiant.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserDto fromEntity(User user) {
        if (user == null) {
            return null;
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        UserDto userDto;
        userDto = modelMapper.map(user, UserDto.class);
        return userDto;

    }

    public User toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        User user;
        user = modelMapper.map(userDto, User.class);
        return user;

    }

}
