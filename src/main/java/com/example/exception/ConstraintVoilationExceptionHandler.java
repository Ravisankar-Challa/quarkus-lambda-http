package com.example.exception;

import java.util.StringJoiner;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.Priority;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.example.model.ErrorMessage;
import com.example.util.Constants;

@Provider
@Priority(Priorities.USER)
public class ConstraintVoilationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    private static final Logger log = Logger.getLogger(ConstraintVoilationExceptionHandler.class.getName());
    
    @Override
    public Response toResponse(ConstraintViolationException cve) {
        String errorString = cve.getConstraintViolations()
                                .stream()
                                .peek(this::logError)
                                .map(ConstraintViolation::getMessage)
                                .collect(Collectors.joining(", "));
        
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode(Status.BAD_REQUEST.getStatusCode());
        errorMessage.setErrorMessage(errorString);
        
        return Response.status(Status.BAD_REQUEST)
                        .entity(errorMessage)
                        .type(Constants.APPLICATION_ERROR_JSON)
                        .build();
    }
    
    public void logError(final ConstraintViolation<?> exception) {
        log.severe(new StringJoiner(".")
                .add(exception.getRootBeanClass().getSimpleName())
                .add(exception.getPropertyPath().toString())
                .add(exception.getMessage().toString())
                .toString());
    }
}