package com.amadou.gestionEtudiant.service;

import com.amadou.gestionEtudiant.dto.EtudiantDto;

import java.util.List;

public interface EtudiantService {

    EtudiantDto save(EtudiantDto etudiantDto);

    EtudiantDto update(EtudiantDto etudiantDto, Integer idEtudiant);

    EtudiantDto findById(Integer id);

    List<EtudiantDto> findAll();

    void delete(Integer id);
}
