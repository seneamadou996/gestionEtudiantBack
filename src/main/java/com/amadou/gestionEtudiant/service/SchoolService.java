package com.amadou.gestionEtudiant.service;

import com.amadou.gestionEtudiant.dto.SchoolDto;

import java.util.List;

public interface SchoolService {

    SchoolDto save(SchoolDto schoolDto);

    SchoolDto update(SchoolDto schoolDto, Integer idSchool);

    SchoolDto findById(Integer id);

    List<SchoolDto> findAll();

    void delete(Integer id);

}
