package com.amadou.gestionEtudiant.handler;

import com.amadou.gestionEtudiant.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDto {

    private Integer httpCode;

    private ErrorCode code;

    private String message;

    private List<String> errors = new ArrayList<>();

}
