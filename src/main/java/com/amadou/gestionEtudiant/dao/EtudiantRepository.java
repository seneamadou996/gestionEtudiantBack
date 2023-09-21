package com.amadou.gestionEtudiant.dao;

import com.amadou.gestionEtudiant.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
}
