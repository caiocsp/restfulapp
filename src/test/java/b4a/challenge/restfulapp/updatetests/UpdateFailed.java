package b4a.challenge.restfulapp.updatetests;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import b4a.challenge.restfulapp.entity.request.CreateTaskRequest;
import b4a.challenge.restfulapp.entity.request.UpdateTaskRequest;
import b4a.challenge.restfulapp.service.TaskService;

@SpringBootTest
public class UpdateFailed {

    @Autowired
    private TaskService taskService;

    @Test
    public void updateFailedTestByEmptyName(){
        CreateTaskRequest task = new CreateTaskRequest();

        task.setName("Teste 1 - Update FALHA");
        task.setDescription("Teste de UPDATE - FALHA!");
        task.setDay(21);
        task.setMonth(10);
        task.setYear(2024);
        taskService.createTask(task);

        UpdateTaskRequest updateParameter = new UpdateTaskRequest(1L,"",task.getDescription(),
         task.getDay(), task.getMonth(), task.getYear());

        assertThrows(ResponseStatusException.class , () -> taskService.updateTask(updateParameter));
    }

    @Test
    public void updateFailedTestByIncorrectDate(){
        CreateTaskRequest task = new CreateTaskRequest();

        task.setName("Teste 1 - Update FALHA");
        task.setDescription("Teste de UPDATE - FALHA!");
        task.setDay(21);
        task.setMonth(10);
        task.setYear(2024);
        taskService.createTask(task);

        UpdateTaskRequest updateParameter = new UpdateTaskRequest(1L,"TesteUpdate",task.getDescription(),
         task.getDay(), task.getMonth(), 2022);

        assertThrows(ResponseStatusException.class , () -> taskService.updateTask(updateParameter));
    }

    @Test
    public void updateFailedTestByEqualDescription(){
        CreateTaskRequest task = new CreateTaskRequest();

        task.setName("Teste 1 - Update FALHA");
        task.setDescription("Teste de UPDATE - FALHA!");
        task.setDay(21);
        task.setMonth(10);
        task.setYear(2024);
        taskService.createTask(task);

        UpdateTaskRequest updateParameter = new UpdateTaskRequest(1L,"TesteUpdate","Teste de UPDATE - FALHA!",
         task.getDay(), task.getMonth(), 2022);

        assertThrows(ResponseStatusException.class , () -> taskService.updateTask(updateParameter));
    }
}
