package com.amadou.gestionEtudiant.controller;

import com.amadou.gestionEtudiant.dto.SchoolDto;
import com.amadou.gestionEtudiant.service.SchoolService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.amadou.gestionEtudiant.utils.Constants.APP_ROOT;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class SchoolController{

    private final SchoolService schoolService;

    @PostMapping(value = APP_ROOT + "/schools/create")
    public ResponseEntity<SchoolDto> save(@RequestBody SchoolDto schoolDto) {
        return ResponseEntity.ok(schoolService.save(schoolDto));
    }

    @PutMapping(value = APP_ROOT + "/schools/update/{idSchool}")
    public ResponseEntity<SchoolDto> update(@RequestBody SchoolDto schoolDto, Integer idSchool) {
        return ResponseEntity.ok(schoolService.update(schoolDto, idSchool));
    }

    @GetMapping(value = APP_ROOT + "/schools/{idSchool}")
    public ResponseEntity<SchoolDto> findById(@PathVariable("idSchool") Integer id) {
        return ResponseEntity.ok(schoolService.findById(id));
    }

    @GetMapping(value = APP_ROOT + "/schools/all")
    public ResponseEntity<List<SchoolDto>> findAll() {
        return ResponseEntity.ok(schoolService.findAll());
    }

    @GetMapping(value = APP_ROOT + "/schools/delete/{idSchool}")
    public ResponseEntity<Void> delete(@PathVariable("idSchool") Integer id) {
        schoolService.delete(id);
        return ResponseEntity.ok().build();
    }
}
