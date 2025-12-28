package caiocsp.project.restfulapp.entity.request;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class UpdateTaskRequest {

    @Id
    @NotNull
    @Column
    private Long id;
    @NotNull
    @Column
    private String name;
    @NotNull
    @Column
    private Integer taskStatus;
    @NotNull
    @Column
    private String description;
    @NotNull
    @Column
    private int day;
    @NotNull
    @Column
    private int month;
    @NotNull
    @Column
    private int year;

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

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public UpdateTaskRequest(@NotNull Long id, @NotNull String name, @NotNull String description, @NotNull int day,
            @NotNull int month, @NotNull int year) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.day = day;
        this.month = month;
        this.year = year;
    }

}
