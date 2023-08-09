package b4a.challenge.restfulapp.createtests;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import b4a.challenge.restfulapp.entity.request.CreateTaskRequest;
import b4a.challenge.restfulapp.service.TaskService;

@SpringBootTest
public class CreateFailed {

    @Autowired
    private TaskService taskService;


    @Test
    public void nullNameErrorTest() {
        CreateTaskRequest task = new CreateTaskRequest();

        task.setName(null);
        task.setDescription("Teste de inserção de nome nulo");
        task.setDay(22);
        task.setMonth(10);
        task.setYear(2023);

        assertThrows(ResponseStatusException.class , () -> taskService.createTask(task));
        
    }

    @Test
    public void InvalidDateErrorTest() {
        CreateTaskRequest task = new CreateTaskRequest();

        task.setName(null);
        task.setDescription("Teste de inserção de data inválida");
        task.setDay(45);
        task.setMonth(10);
        task.setYear(2026);

        assertThrows(ResponseStatusException.class , () -> taskService.createTask(task));
        
    }

}
