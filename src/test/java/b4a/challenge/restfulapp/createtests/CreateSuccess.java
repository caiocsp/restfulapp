package b4a.challenge.restfulapp.createtests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import b4a.challenge.restfulapp.entity.Task;
import b4a.challenge.restfulapp.entity.request.CreateTaskRequest;
import b4a.challenge.restfulapp.service.TaskService;

@SpringBootTest
public class CreateSuccess {

    @Autowired
    TaskService taskService;
    
    @Test
    public void createSuccessTest() {
        CreateTaskRequest task = new CreateTaskRequest();

        task.setName("Teste 1 - Sucesso!");
        task.setDescription("Teste de inserção com sucesso!");
        task.setDay(18);
        task.setMonth(12);
        task.setYear(2023);
        Task newTask = taskService.createTask(task);

        assertEquals(newTask.getName(), "Teste 1 - Sucesso!");
        
    }


}
