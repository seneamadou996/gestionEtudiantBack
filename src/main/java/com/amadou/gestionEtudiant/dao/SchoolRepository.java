package com.amadou.gestionEtudiant.dao;

import com.amadou.gestionEtudiant.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {

    School findByName(String name);

}
