package org.example.exception;

import lombok.Getter;


@Getter
public class AuthServiceException extends RuntimeException{
    /**
     * Uygujlama içinde fırlatılacak olan özelleştirilmiş hatalar için kullanılacaktır.
     */
    private final ErrorType errorType;

    public AuthServiceException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public AuthServiceException(ErrorType errorType, String customMessage){
        super(customMessage);
        this.errorType = errorType;
    }

}