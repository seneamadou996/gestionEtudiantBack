package com.amadou.gestionEtudiant.controller;

import com.amadou.gestionEtudiant.dto.ChangeUserPasswordDto;
import com.amadou.gestionEtudiant.dto.RoleDto;
import com.amadou.gestionEtudiant.dto.RoleUserFormDto;
import com.amadou.gestionEtudiant.dto.UserDto;
import com.amadou.gestionEtudiant.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.amadou.gestionEtudiant.utils.Constants.APP_ROOT;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;

    @PostMapping(value = APP_ROOT + "/users/create")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.saveUser(userDto));
    }

    @PutMapping(value = APP_ROOT + "/users/update/{idUser}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("idUser") Integer idUser) {
        return ResponseEntity.ok(this.userService.updateUser(userDto, idUser));
    }

    @GetMapping(value = APP_ROOT + "/users/{idUser}")
    public ResponseEntity<UserDto> findUserById(@PathVariable("idUser") Integer idUser) {
        return ResponseEntity.ok(userService.findUserById(idUser));
    }

    @GetMapping(value = APP_ROOT + "/users/findUserByUsername/{username}")
    public ResponseEntity<UserDto> findUserByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.findUserByUsername(username));
    }

    @GetMapping(value = APP_ROOT + "/users/all")
    public ResponseEntity<List<UserDto>> findAllUser() {
        return ResponseEntity.ok(userService.findAllUser());
    }

    @DeleteMapping(value = APP_ROOT + "/users/delete/{idUser}")
    public ResponseEntity<Void> deleteUser(@PathVariable("idUser") Integer idUser) {
        userService.deleteUser(idUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = APP_ROOT + "/roles/create")
    public ResponseEntity<RoleDto> saveRole(@RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(userService.saveRole(roleDto));
    }

    @GetMapping(value = APP_ROOT + "/roles/all")
    public ResponseEntity<List<RoleDto>> findAllRole() {
        return ResponseEntity.ok(userService.findAllRole());
    }

    @PostMapping(value = APP_ROOT + "/users/changePassword")
    public ResponseEntity<UserDto> changePassword(@RequestBody ChangeUserPasswordDto dto) {
        return ResponseEntity.ok(userService.changePassword(dto));
    }

    @DeleteMapping(value = APP_ROOT + "/roles/delete/{idRole}")
    public ResponseEntity<Void> deleteRole(@PathVariable("idRole") Integer id) {
        userService.deleteRole(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = APP_ROOT + "/users/addtouser")
    public ResponseEntity<Void> addRoleToUser(@RequestBody RoleUserFormDto roleUserFormDto) {
        userService.addRoleToUser(roleUserFormDto.getUsername(), roleUserFormDto.getRolename());
        return ResponseEntity.ok().build();
    }
}
