package caiocsp.project.restfulapp.entity.request;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class UpdateTaskDescription {

    @Id
    @NotNull
    private Long id;
    @NotNull
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
