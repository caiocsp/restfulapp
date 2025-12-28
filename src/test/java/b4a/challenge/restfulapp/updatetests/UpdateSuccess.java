package b4a.challenge.restfulapp.updatetests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import caiocsp.project.restfulapp.entity.Task;
import caiocsp.project.restfulapp.entity.request.CreateTaskRequest;
import caiocsp.project.restfulapp.entity.request.UpdateNameOfTaskRequest;
import caiocsp.project.restfulapp.entity.request.UpdateTaskRequest;
import caiocsp.project.restfulapp.repository.TaskRepository;
import caiocsp.project.restfulapp.service.TaskService;

@SpringBootTest
public class UpdateSuccess {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void updateTaskNameSuccess() {
        CreateTaskRequest task = new CreateTaskRequest();

        task.setName("Teste 1 - Update Sucesso!");
        task.setDescription("Teste de UPDATE Sucesso!");
        task.setDay(21);
        task.setMonth(10);
        task.setYear(2024);
        taskService.createTask(task);

        UpdateTaskRequest updateParameter = new UpdateTaskRequest(1L, "Teste 1 de Update SUCESSO", "Nova descrição!",
                22, 10, 2023);
        taskService.updateTask(updateParameter);
        Optional<Task> taskUpdated = taskRepository.findById(1L);

        assertEquals(updateParameter.getName(), taskUpdated.get().getName());
    }

    @Test
    public void updateTaskDescriptionSuccess() {
        CreateTaskRequest task = new CreateTaskRequest();

        task.setName("Teste 1 - Update Sucesso!");
        task.setDescription("Teste de UPDATE Sucesso!");
        task.setDay(21);
        task.setMonth(10);
        task.setYear(2024);
        taskService.createTask(task);

        UpdateNameOfTaskRequest updateParameter = new UpdateNameOfTaskRequest();
        updateParameter.setId(1L);
        updateParameter.setName("New Task Name");
        taskService.updateNameOfTask(updateParameter);
        Optional<Task> taskUpdated = taskRepository.findById(1L);

        assertEquals(updateParameter.getName(), taskUpdated.get().getName());
    }

}
