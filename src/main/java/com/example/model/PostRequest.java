package com.example.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class PostRequest {

    @NotNull(message = "College is empty")
    @Pattern(regexp = "[a-zA-z ]*", message = "College is not valid")
    private String college;
    
    @Min(value = 1, message = "Age is not valid")
    @Max(value = 120, message = "Age is not valid")
    private Integer age;

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}