package com.amadou.gestionEtudiant.controller;

import com.amadou.gestionEtudiant.dto.EtudiantDto;
import com.amadou.gestionEtudiant.service.EtudiantService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.amadou.gestionEtudiant.utils.Constants.APP_ROOT;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class EtudiantController {

    private final EtudiantService etudiantService;

    @PostMapping(value = APP_ROOT + "/etudiants/create")
    public ResponseEntity<EtudiantDto> save(@RequestBody EtudiantDto etudiantDto) {
        return ResponseEntity.ok(etudiantService.save(etudiantDto));
    }

    @PutMapping(value = APP_ROOT + "/etudiants/update/{idEtudiant}")
    public ResponseEntity<EtudiantDto> update(@RequestBody EtudiantDto etudiantDto, @PathVariable("idEtudiant") Integer idEtudiant) {
        return ResponseEntity.ok(etudiantService.update(etudiantDto, idEtudiant));
    }

    @GetMapping(value = APP_ROOT + "/etudiants/{idEtudiant}")
    public ResponseEntity<EtudiantDto> findById(@PathVariable("idEtudiant") Integer id) {
        return ResponseEntity.ok(etudiantService.findById(id));
    }

    @GetMapping(value = APP_ROOT + "/etudiants/all")
    public ResponseEntity<List<EtudiantDto>> findAll() {
        return ResponseEntity.ok(etudiantService.findAll());
    }

    @DeleteMapping(value = APP_ROOT + "/etudiants/delete/{idEtudiant}")
    public ResponseEntity<Void> delete(@PathVariable("idEtudiant") Integer id) {
        etudiantService.delete(id);
        return ResponseEntity.ok().build();
    }
}
