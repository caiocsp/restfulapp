package b4a.challenge.restfulapp.deletetests;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import b4a.challenge.restfulapp.entity.request.CreateTaskRequest;
import b4a.challenge.restfulapp.service.TaskService;

@SpringBootTest
public class DeleteSuccess {
    
    @Autowired
    TaskService taskService;
    @Test
    public void deleteSuccessTest() {

        CreateTaskRequest task = new CreateTaskRequest();

        task.setName("Teste 1 - Sucesso!");
        task.setDescription("Teste de inserção com sucesso!");
        task.setDay(18);
        task.setMonth(12);
        task.setYear(2023);
        taskService.createTask(task);
        HashMap<String, Object> deleted =  taskService.deleteTaskById(1L);
        
        HashMap<String, Object> resultExample = new  HashMap<String, Object> ();
        resultExample.put("result", "Tarefa " + 1L + " apagada com sucesso");

        assertEquals(deleted.get("result"), resultExample.get("result"));
    }
}
