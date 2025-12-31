package caiocsp.project.restfulapp.entity.request;

import org.springframework.lang.NonNull;

public class UpdateDeadlineOfTaskRequest {

    @NonNull
    private Long id;
    @NonNull
    private int day;
    @NonNull
    private int Month;
    @NonNull
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
