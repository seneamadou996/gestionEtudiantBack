package com.amadou.gestionEtudiant.service.impl;

import com.amadou.gestionEtudiant.Validator.RoleValidator;
import com.amadou.gestionEtudiant.Validator.UserValidator;
import com.amadou.gestionEtudiant.dao.RoleRepository;
import com.amadou.gestionEtudiant.dao.UserRepository;
import com.amadou.gestionEtudiant.dto.ChangeUserPasswordDto;
import com.amadou.gestionEtudiant.dto.RoleDto;
import com.amadou.gestionEtudiant.dto.UserDto;
import com.amadou.gestionEtudiant.exception.EntityNotFoundException;
import com.amadou.gestionEtudiant.exception.ErrorCode;
import com.amadou.gestionEtudiant.exception.InvalidEntityException;
import com.amadou.gestionEtudiant.exception.InvalidOperationException;
import com.amadou.gestionEtudiant.mapper.RoleMapper;
import com.amadou.gestionEtudiant.mapper.UserMapper;
import com.amadou.gestionEtudiant.model.User;
import com.amadou.gestionEtudiant.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto saveUser(UserDto userDto) {

        List<String> erros = UserValidator.validate(userDto);
        if (!erros.isEmpty()) {
            log.error("Object is not valid");
            throw new InvalidEntityException("The User object is not valid", ErrorCode.USER_NOT_VALID, erros);
        }

        if (userAlreadyExist(userDto.getUsername())) {
            log.error("User already exist");
            throw new RuntimeException("User already exist");
        }

        userDto.setPassword(this.passwordEncoder.encode(userDto.getPassword()));

        UserDto savedUser = userMapper.fromEntity(userRepository.save(userMapper.toEntity(userDto)));

        RoleDto role = roleMapper.fromEntity(roleRepository.findRoleByRoleName("ROLE_USER"));
        if (role == null){
            RoleDto roleDto = new RoleDto();
            roleDto.setRoleName("ROLE_USER");
            RoleDto savedRole = roleMapper.fromEntity(roleRepository.save(roleMapper.toEntity(roleDto)));
            addRoleToUser(userDto.getUsername(), savedRole.getRoleName());
        }else {
            addRoleToUser(userDto.getUsername(), role.getRoleName());
        }


        return savedUser;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer idUser) {
        List<String> erros = UserValidator.validate(userDto);
        if (!erros.isEmpty()) {
            log.error("Object is not valid");
            throw new InvalidEntityException("The User object is not valid", ErrorCode.USER_NOT_VALID, erros);
        }
        UserDto dto = findUserById(idUser);
        if (dto.getId() != null) {
            userDto.setId(dto.getId());
        }
        return userMapper.fromEntity(
                userRepository.save(userMapper.toEntity(userDto))
        );
    }


    private boolean userAlreadyExist(String username) {
        User user = userRepository.findByUsername(username);
        return (user != null);
    }

    @Override
    public UserDto findUserById(Integer idUser) {
        if (idUser == null) {
            log.error("User ID is null");
            return null;
        }
        return userRepository.findById(idUser)
                .map(userMapper::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No User with ID "+idUser, ErrorCode.USER_NOT_FOUND
                ));
    }

    @Override
    public UserDto findUserByUsername(String username) {
        if (username == null) {
            log.error("User name is null");
            return null;
        }
        return userMapper.fromEntity(userRepository.findByUsername(username));
    }

    @Override
    public List<UserDto> findAllUser() {
        return userRepository.findAll().stream()
                .map(userMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer idUser) {
        if (idUser == null) {
            log.error("User ID is null");
            return;
        }
        userRepository.deleteById(idUser);
    }

    @Override
    public RoleDto saveRole(RoleDto roleDto) {
        List<String> errors = RoleValidator.validate(roleDto);
        if (!errors.isEmpty()) {
            log.error("Object is not valid");
            throw new InvalidEntityException("The Role object is not valid", ErrorCode.ROLE_NOT_VALID, errors);
        }
        return roleMapper.fromEntity(
                roleRepository.save(
                        roleMapper.toEntity(roleDto)
                )
        );
    }

    @Override
    public List<RoleDto> findAllRole() {
        return roleRepository.findAll().stream()
                .map(roleMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto changePassword(ChangeUserPasswordDto dto) {
        validate(dto);
        Optional<User> user = userRepository.findById(dto.getId());
        if (user.isEmpty()) {
            log.warn("Cannot change password with null object");
            throw new InvalidOperationException(
                    "No information was provided to be able to change the password",
                    ErrorCode.USER_CHANGE_PASSWORD_OBJECT_NOT_VALID
            );
        }
        User userOptional = user.get();
        userOptional.setPassword(this.passwordEncoder.encode(dto.getPassword()));
        return userMapper.fromEntity(userRepository.save(user.get()));
    }

    private void validate(ChangeUserPasswordDto dto){

        if (dto == null){
            log.warn("Impossible de modifier le mot de passe avec un objet NULL");
            throw new InvalidOperationException("Aucune information n'a ete fourni pour pouvoir chager le mot de passe",
                    ErrorCode.USER_CHANGE_PASSWORD_OBJECT_NOT_VALID
            );
        }

        if (dto.getId() == null){
            log.warn("Impossible de modifier le mot de passe avec un ID NULL");
            throw new InvalidOperationException("ID utilisateur null:: Impossible de modifier le mot de passe",
                    ErrorCode.USER_CHANGE_PASSWORD_OBJECT_NOT_VALID
            );
        }

        if (!StringUtils.hasLength(dto.getPassword()) || !StringUtils.hasLength(dto.getConfirmPassword())){
            log.warn("Impossible de modifier le mot de passe avec un mot de passe NULL");
            throw new InvalidOperationException("Mot de passe utilisateur null:: Impossible de modifier le mot de passe",
                    ErrorCode.USER_CHANGE_PASSWORD_OBJECT_NOT_VALID
            );
        }

        if (!dto.getPassword().equals(dto.getConfirmPassword())){
            log.warn("Impossible de modifier le mot de passe avec deux mots de passe differents");
            throw new InvalidOperationException("Mots passe utilisateur non conformes:: Impossible de modifier le mot de passe",
                    ErrorCode.USER_CHANGE_PASSWORD_OBJECT_NOT_VALID
            );
        }
    }

    @Override
    public void deleteRole(Integer id) {
        if (id == null) {
            log.error("User ID is null");
            return;
        }
        roleRepository.deleteById(id);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        RoleDto roleDto = roleMapper.fromEntity(roleRepository.findRoleByRoleName(rolename));
        UserDto userDto = userMapper.fromEntity(userRepository.findByUsername(username));

        userDto.getRolesDtos().add(roleDto);

        userRepository.save(userMapper.toEntity(userDto));
    }
}
