package com.amadou.gestionEtudiant.exception;

public enum ErrorCode {

    ETUDIANT_NOT_FOUND(1000),
    ETUDIANT_NOT_VALID(1001),
    ROLE_NOT_FOUND(2000),
    ROLE_NOT_VALID(2001),
    USER_NOT_FOUND(3000),
    USER_NOT_VALID(3001),
    USER_CHANGE_PASSWORD_OBJECT_NOT_VALID(3002),
    SCHOOL_NOT_FOUND(4000),
    SCHOOL_NOT_VALID(4001),
    SCHOOL_ALREADY_IN_USE(4002),
    UPDATE_PHOTO_EXCEPTION(5000),
    UNKNOWN_CONTEXT(6000);

    private int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
