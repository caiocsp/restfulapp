package caiocsp.project.restfulapp.entity.enums;

public enum TaskStatus {

    NEW(1), ONGOING(2), ENDED(3), BLOCKED(4), CANCELLED(5);

    public final Integer id;

    TaskStatus(Integer id) {
        this.id = id;
    }

}
