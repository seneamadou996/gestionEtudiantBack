package com.amadou.gestionEtudiant.service.impl;

import com.amadou.gestionEtudiant.Validator.SchoolValidator;
import com.amadou.gestionEtudiant.dao.SchoolRepository;
import com.amadou.gestionEtudiant.dao.UserRepository;
import com.amadou.gestionEtudiant.dto.RoleDto;
import com.amadou.gestionEtudiant.dto.RoleUserFormDto;
import com.amadou.gestionEtudiant.dto.SchoolDto;
import com.amadou.gestionEtudiant.dto.UserDto;
import com.amadou.gestionEtudiant.exception.EntityNotFoundException;
import com.amadou.gestionEtudiant.exception.ErrorCode;
import com.amadou.gestionEtudiant.exception.InvalidEntityException;
import com.amadou.gestionEtudiant.exception.InvalidOperationException;
import com.amadou.gestionEtudiant.mapper.SchoolMapper;
import com.amadou.gestionEtudiant.model.School;
import com.amadou.gestionEtudiant.model.User;
import com.amadou.gestionEtudiant.service.SchoolService;
import com.amadou.gestionEtudiant.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public SchoolDto save(SchoolDto schoolDto) {

        List<String> errors = SchoolValidator.validate(schoolDto);

        if (!errors.isEmpty()) {
            log.error("Object is not valid {}", schoolDto);
            throw new InvalidEntityException("The School object is not valid", ErrorCode.SCHOOL_NOT_VALID, errors);
        }

        if (schoolAlreadyExist(schoolDto.getName())) {
            log.error("School already exist");
            throw new RuntimeException("School already exist");
        }

        SchoolDto savedSchool = schoolMapper.fromEntity(schoolRepository.save(schoolMapper.toEntity(schoolDto)));

        UserDto  userDto = fromSchool(schoolDto);
        UserDto savedUser = this.userService.saveUser(userDto);

        RoleDto roleDto = new RoleDto();
        roleDto.setRoleName("ROLE_ADMIN");

        RoleDto savedRole = this.userService.saveRole(roleDto);

        RoleUserFormDto roleUserFormDto = RoleUserFormDto.builder()
                .username(savedUser.getUsername())
                .rolename(savedRole.getRoleName())
                .build();

        this.userService.addRoleToUser(roleUserFormDto.getUsername(), roleUserFormDto.getRolename());

        return savedSchool;
    }

    @Override
    public SchoolDto update(SchoolDto schoolDto, Integer idSchool) {
        List<String> errors = SchoolValidator.validate(schoolDto);
        if (!errors.isEmpty()) {
            log.error("Object is not valid {}", schoolDto);
            throw new InvalidEntityException("The School object is not valid", ErrorCode.SCHOOL_NOT_VALID, errors);
        }
        SchoolDto school = findById(idSchool);
        if (school.getId() != null) {
            schoolDto.setId(school.getId());
        }
        return schoolMapper.fromEntity(
                schoolRepository.save(schoolMapper.toEntity(schoolDto))
        );
    }

    private boolean schoolAlreadyExist(String name) {
        School school = schoolRepository.findByName(name);
        return (school != null);
    }

    private UserDto fromSchool(SchoolDto dto) {

        UserDto userDto = new UserDto();

        userDto.setFirstName(dto.getName());
        userDto.setLastName(dto.getNumberTel());
        userDto.setAddessDto(dto.getAddressDto());
        userDto.setEmail(dto.getName().toLowerCase()+"@gmail.com");
        userDto.setNumberTel(dto.getNumberTel());
        userDto.setUsername(dto.getName().toLowerCase());
        userDto.setPassword(generateRandomPassword());
        userDto.setPhoto(dto.getPhoto());
        userDto.setSchoolDto(dto);

        return userDto;
    }

    private String generateRandomPassword() {
        return "som3R@nd0mP@$$word";
    }

    @Override
    public SchoolDto findById(Integer id) {
        if (id == null) {
            log.error("School ID is null");
            return null;
        }
        return schoolRepository.findById(id)
                .map(schoolMapper::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "No school with ID "+ id, ErrorCode.SCHOOL_NOT_FOUND
                ));
    }

    @Override
    public List<SchoolDto> findAll() {
        return schoolRepository.findAll().stream()
                .map(schoolMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("School ID is null");
            return;
        }
        List<User> users = userRepository.findBySchoolId(id);
        if (!users.isEmpty()) {
            throw new InvalidOperationException(
                    "Cannot delete a school already used in users table", ErrorCode.SCHOOL_ALREADY_IN_USE
            );
        }
        schoolRepository.deleteById(id);

    }
}
