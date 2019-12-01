package com.example.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class GetRequest {

    @HeaderParam("version")
    @NotNull(message = "Version Missing")
    @Pattern(regexp = "^(0|[1-9]\\d*)(\\.\\d+)$", message = "Version incorrect format")
    private String version;
    
    @PathParam("name")
    @NotNull(message = "Name Missing")
    @Pattern(regexp = "[a-zA-z]*", message = "Name format is incorrect")
    private String name;
    
    @QueryParam("question")
    @NotNull(message = "Question Missing")
    @Pattern(regexp = "[a-z A-z]*", message = "Question format is incorrect")
    private String question;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    
    
}