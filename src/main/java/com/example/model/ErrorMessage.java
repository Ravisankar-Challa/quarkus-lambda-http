package com.example.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ErrorMessage {

    private Integer errorCode;
    private String errorMessage;
    
    public Integer getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}