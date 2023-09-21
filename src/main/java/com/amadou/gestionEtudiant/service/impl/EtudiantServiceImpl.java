package com.amadou.gestionEtudiant.service.impl;

import com.amadou.gestionEtudiant.Validator.EtudiantValidator;
import com.amadou.gestionEtudiant.dao.EtudiantRepository;
import com.amadou.gestionEtudiant.dto.EtudiantDto;
import com.amadou.gestionEtudiant.exception.EntityNotFoundException;
import com.amadou.gestionEtudiant.exception.ErrorCode;
import com.amadou.gestionEtudiant.exception.InvalidEntityException;
import com.amadou.gestionEtudiant.mapper.EtudiantMapper;
import com.amadou.gestionEtudiant.service.EtudiantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EtudiantServiceImpl implements EtudiantService {

    private final EtudiantRepository etudiantRepository;
    private final EtudiantMapper etudiantMapper;

    @Override
    public EtudiantDto save(EtudiantDto etudiantDto) {

        List<String> errors = EtudiantValidator.validate(etudiantDto);
        if (!errors.isEmpty()) {
            log.error("Object is not valid");
            throw new InvalidEntityException("The Etudiant object is not valid", ErrorCode.ETUDIANT_NOT_VALID, errors);
        }

        return etudiantMapper.fromEntity(
                etudiantRepository.save(
                        etudiantMapper.toEntity(etudiantDto)
                )
        );
    }

    @Override
    public EtudiantDto update(EtudiantDto etudiantDto, Integer idEtudiant) {
        List<String> errors = EtudiantValidator.validate(etudiantDto);
        if (!errors.isEmpty()) {
            log.error("Object is not valid");
            throw new InvalidEntityException("The Etudiant object is not valid", ErrorCode.ETUDIANT_NOT_VALID, errors);
        }
        EtudiantDto etud = findById(idEtudiant);
        if (etud.getId() != null) {
            etudiantDto.setId(etud.getId());
        }
        return etudiantMapper.fromEntity(
                etudiantRepository.save(
                        etudiantMapper.toEntity(etudiantDto)
                )
        );
    }

    @Override
    public EtudiantDto findById(Integer id) {
        if (id == null) {
            log.error("Etudiant ID is null");
            return null;
        }
        return etudiantRepository.findById(id)
                .map(etudiantMapper::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No Student with ID "+id, ErrorCode.ETUDIANT_NOT_FOUND
                ));
    }

    @Override
    public List<EtudiantDto> findAll() {
        return etudiantRepository.findAll().stream()
                .map(etudiantMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Etudiant ID is null");
            return;
        }

        etudiantRepository.deleteById(id);
    }
}
