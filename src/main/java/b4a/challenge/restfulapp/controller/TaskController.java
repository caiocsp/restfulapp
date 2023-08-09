package b4a.challenge.restfulapp.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import b4a.challenge.restfulapp.entity.RestResponse;
import b4a.challenge.restfulapp.entity.Task;
import b4a.challenge.restfulapp.entity.request.CreateTaskRequest;
import b4a.challenge.restfulapp.entity.request.UpdateDeadlineOfTaskRequest;
import b4a.challenge.restfulapp.entity.request.UpdateNameOfTaskRequest;
import b4a.challenge.restfulapp.entity.request.UpdateTaskDescription;
import b4a.challenge.restfulapp.entity.request.UpdateTaskRequest;
import b4a.challenge.restfulapp.service.TaskService;

@RestController
@RequestMapping(value = "/Task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTasks() {
        List<Task> result = taskService.getTasks();
		return ResponseEntity.ok().body(RestResponse.response(result));
    }

    @GetMapping(value="/detail/{taskId}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTaskById(@PathVariable Long taskId){
        Task result = taskService.getTaskById(taskId);
        return ResponseEntity.ok().body(RestResponse.response(result));
    }

    @GetMapping(value="/paginatedTasks", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTaskPaginated(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize){
        HashMap<String, Object> result = taskService.getPaginatedTasks(page, pageSize);
        return ResponseEntity.ok().body(RestResponse.response(result));
    }

    @PostMapping(value="/create", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createTask(@RequestBody CreateTaskRequest requestBody){
        Task result = taskService.createTask(requestBody);
        return ResponseEntity.ok().body(RestResponse.response(result));
    }

    @PutMapping(value="/updateTask", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateTask(@RequestBody UpdateTaskRequest requestBody) {
        HashMap<String, Object> result = taskService.updateTask(requestBody);
        return ResponseEntity.ok().body(RestResponse.response(result));
    }

    @PutMapping(value="/updateName", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateTaskById(@RequestBody UpdateNameOfTaskRequest requestBody) {
        HashMap<String, Object> result = taskService.updateNameOfTask(requestBody);
        return ResponseEntity.ok().body(RestResponse.response(result));
    }

    @PutMapping(value="/updateTaskDescription", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateTaskById(@RequestBody UpdateTaskDescription requestBody) {
        HashMap<String, Object> result = taskService.updateTaskDescription(requestBody);
        return ResponseEntity.ok().body(RestResponse.response(result));
    }

    @PutMapping(value="/updateDeadline", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateTaskById(@RequestBody UpdateDeadlineOfTaskRequest requestBody) {
        HashMap<String, Object> result = taskService.updateDeadlineOfTask(requestBody);
        return ResponseEntity.ok().body(RestResponse.response(result));
    }

    @PostMapping(value="/delete/{taskId}")
    public ResponseEntity<Object> deleteTaskById(@PathVariable Long  taskId)  {
        HashMap<String, Object> result = taskService.deleteTaskById(taskId);
		return ResponseEntity.ok().body(RestResponse.response(result));
    }

}
