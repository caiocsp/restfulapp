package caiocsp.project.restfulapp.entity.request;

import jakarta.validation.constraints.NotNull;

public class UpdateDeadlineOfTaskRequest {

    @NotNull
    private Long id;
    @NotNull
    @NotNull
    private int day;
    @NotNull
    private int Month;
    @NotNull
    private int Year;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
