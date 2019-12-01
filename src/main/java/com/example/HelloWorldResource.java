package com.example;

import java.util.StringJoiner;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.model.GetRequest;
import com.example.model.PostRequest;

@Path("/api")
@ApplicationScoped
public class HelloWorldResource {

    @GET
    @Path("/hello")
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        return "{\"hello\": \"world\"}";
    }

    @GET
    @Path("get/validate/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String validateGetRequest(@Valid @BeanParam GetRequest request) {
        return new StringJoiner(" ")
                .add(request.getVersion())
                .add(request.getName())
                .add(request.getQuestion()).toString();
    }
    
    @POST
    @Path("post/validate/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PostRequest validatePostRequest(
                        @PathParam("name")
                        @NotNull(message = "Name Missing")
                        @Pattern(regexp = "[a-zA-Z \']*", message = "Name format is incorrect")
                        final String name,
                        @Valid final PostRequest request) {
        return request;
    }
}