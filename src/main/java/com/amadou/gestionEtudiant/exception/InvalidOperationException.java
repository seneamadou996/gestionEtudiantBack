package com.amadou.gestionEtudiant.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InvalidOperationException extends RuntimeException {

    private ErrorCode errorCode;

    public InvalidOperationException(String message) {
        super(message);
    }

    public InvalidOperationException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
