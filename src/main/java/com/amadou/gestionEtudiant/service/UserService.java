package com.amadou.gestionEtudiant.service;

import com.amadou.gestionEtudiant.dto.ChangeUserPasswordDto;
import com.amadou.gestionEtudiant.dto.RoleDto;
import com.amadou.gestionEtudiant.dto.RoleUserFormDto;
import com.amadou.gestionEtudiant.dto.UserDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Integer idUser);

    UserDto findUserById(Integer idUser);

    UserDto findUserByUsername(String username);

    List<UserDto> findAllUser();

    void deleteUser(Integer idUser);

    RoleDto saveRole(RoleDto roleDto);

    List<RoleDto> findAllRole();

    UserDto changePassword(ChangeUserPasswordDto dto);

    void deleteRole(Integer id);

    void addRoleToUser(String username, String rolename);

}
