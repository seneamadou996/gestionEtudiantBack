package com.amadou.gestionEtudiant.service.auth;

import com.amadou.gestionEtudiant.dto.UserDto;
import com.amadou.gestionEtudiant.model.auth.ExtendedUser;
import com.amadou.gestionEtudiant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public ApplicationUserDetailsService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDto userDto = userService.findUserByUsername(username);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userDto.getRolesDtos().forEach(roleDto -> authorities.add(new SimpleGrantedAuthority(roleDto.getRoleName())));

        return new ExtendedUser(userDto.getUsername(), userDto.getPassword(), userDto.getSchoolDto().getId(), authorities);
    }
}
