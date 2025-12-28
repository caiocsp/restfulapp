package b4a.challenge.restfulapp.createtests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import caiocsp.project.restfulapp.entity.Task;
import caiocsp.project.restfulapp.entity.request.CreateTaskRequest;
import caiocsp.project.restfulapp.service.TaskService;

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
