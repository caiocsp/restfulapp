package caiocsp.project.restfulapp.entity.request;

import jakarta.validation.constraints.NotNull;

public class CreateTaskRequest {

    @NotNull
    private String name;
    private String description;
    @NotNull
    private int day;
    @NotNull
    private int Month;
    @NotNull
    private int Year;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return Month;
    }

    public void setMonth(int month) {
        Month = month;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

}
