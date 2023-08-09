package b4a.challenge.restfulapp.deletetests;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import b4a.challenge.restfulapp.service.TaskService;


@SpringBootTest
public class DeleteFailed {

    @Autowired
    TaskService taskService;

    @Test
    public void failDeleteTest(){

        assertThrows(ResponseStatusException.class , () -> taskService.deleteTaskById(5L));

    }

}
