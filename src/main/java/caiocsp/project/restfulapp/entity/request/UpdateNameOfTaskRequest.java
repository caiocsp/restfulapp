package caiocsp.project.restfulapp.entity.request;

import jakarta.validation.constraints.NotNull;

public class UpdateNameOfTaskRequest {

    @NotNull
    private Long id;
    @NotNull
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }
}
