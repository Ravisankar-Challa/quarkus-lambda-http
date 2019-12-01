package com.example.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.example.model.ErrorMessage;
import com.example.util.Constants;

@Provider
public class ApplicationExceptionHandler implements ExceptionMapper<ApplicationException>{

    @Override
    public Response toResponse(ApplicationException exception) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode(exception.getErrorCode());
        errorMessage.setErrorMessage(exception.getErrorMessage());
        
        return Response.status(exception.getErrorCode())
                        .entity(errorMessage)
                        .type(Constants.APPLICATION_ERROR_JSON)
                        .build();
    }
    
}