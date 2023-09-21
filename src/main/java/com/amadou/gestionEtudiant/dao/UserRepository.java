package com.amadou.gestionEtudiant.dao;

import com.amadou.gestionEtudiant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    List<User> findBySchoolId(Integer idSchool);

}
