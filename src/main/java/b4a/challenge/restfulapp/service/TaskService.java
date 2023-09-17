package b4a.challenge.restfulapp.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.task.TaskExecutorCustomizer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import b4a.challenge.restfulapp.entity.Task;
import b4a.challenge.restfulapp.entity.enums.TaskStatus;
import b4a.challenge.restfulapp.entity.request.CreateTaskRequest;
import b4a.challenge.restfulapp.entity.request.UpdateDeadlineOfTaskRequest;
import b4a.challenge.restfulapp.entity.request.UpdateNameOfTaskRequest;
import b4a.challenge.restfulapp.entity.request.UpdateTaskDescription;
import b4a.challenge.restfulapp.entity.request.UpdateTaskRequest;
import b4a.challenge.restfulapp.entity.request.UpdateTaskStatusRequest;
import b4a.challenge.restfulapp.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public Task createTask(CreateTaskRequest requestBody) {

        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Task task = new Task();
        
		if(requestBody.getName() == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insira valor não nulo!");

        if(requestBody.getName().length() > 25) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O nome deve conter até 25 caracteres!");
        }

        if(requestBody.getDescription().length() > 250) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A descrição deve conter até 250 caracteres!");
        }

        if(!isValidDate(requestBody.getDay(), requestBody.getMonth(), requestBody.getYear()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data inválida!");

        String deadline = requestBody.getYear() +  "-" + requestBody.getMonth() + "-" + requestBody.getDay() + " 23:59:59.000000000";
		task.setDeadline(Timestamp.valueOf(deadline));

        Timestamp today = new Timestamp(new Date().getTime());
        //Chek if the deadline is before today
        if(task.getDeadline().before(today)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data inválida!");
        }

        task.setName(requestBody.getName());
        task.setTaskStatus(TaskStatus.NEW.id);
        task.setDescription(requestBody.getDescription());
        task.setDateCreated(new Timestamp(new Date().getTime()));
        taskRepository.save(task);
        
        return  taskRepository.save(task);

	}

    public HashMap<String, Object> updateTask(UpdateTaskRequest requestBody) {

        Optional<Task> task = taskRepository.findById(requestBody.getId());
        
		if(requestBody.getName() == null || requestBody.getDescription() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insira valor não nulo!");
        }

        if(requestBody.getName().length() > 25) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O nome deve conter até 25 caracteres!");
        }

        if(requestBody.getDescription().length() > 250) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A descrição deve conter até 250 caracteres!");
        }
        
        if(task.isPresent()){
            //Checking the new Name
            if(requestBody.getName().equals(task.get().getName()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insira um nome diferente!");
            
            if(requestBody.getDescription().equals(task.get().getDescription()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insira uma descrição diferente!");

            if(requestBody.getName().equals(""))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome em branco!");

            if(requestBody.getDescription().equals(""))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Descrição em branco!");
            
            if(requestBody.getTaskStatus() < TaskStatus.NEW.id || requestBody.getTaskStatus() > TaskStatus.CANCELLED.id) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status inválido!");
            }

            if(!isValidDate(requestBody.getDay(), requestBody.getMonth(), requestBody.getYear()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data inválida!");

            String deadline = requestBody.getYear() +  "-" + requestBody.getMonth() + "-" + requestBody.getDay() + " 23:59:59.000000000";

            Timestamp today = new Timestamp(new Date().getTime());

            //Chek if the deadline is before today
            if(Timestamp.valueOf(deadline).before(today)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data inválida!");
            }

            //Check if is the same date of before
            if(task.get().getDeadline().equals(Timestamp.valueOf(deadline))){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data já cadastrada");
            }

            task.get().setName(requestBody.getName());
            task.get().setTaskStatus(requestBody.getTaskStatus());
            task.get().setDescription(requestBody.getDescription());
            task.get().setDeadline(Timestamp.valueOf(deadline));

            taskRepository.save(task.get());

            HashMap<String, Object> result = new  HashMap<String, Object> ();

            result.put("result", task.get());
            return result;

        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");

    }

    public HashMap<String, Object> updateNameOfTask(UpdateNameOfTaskRequest requestBody) {

        Optional<Task> task = taskRepository.findById(requestBody.getId());

        if(requestBody.getName() == null){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insira valor não nulo!");
        }

        if(requestBody.getName().length() > 25) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O nome deve conter até 25 caracteres!");
        }
        
        if(task.isPresent()){
            //Checking the new Name
            if(requestBody.getName().equals(task.get().getName()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insira um nome diferente");

            if(requestBody.getName().equals(""))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome em branco");
            
            task.get().setName(requestBody.getName());
            taskRepository.save(task.get());

            HashMap<String, Object> result = new  HashMap<String, Object> ();
            result.put("result", task);
            return result;                
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");

    }

    public HashMap<String, Object> updateTaskStatus(UpdateTaskStatusRequest requestBody) {

        Optional<Task> task = taskRepository.findById(requestBody.getId());

        if(requestBody.getTaskStatus() < TaskStatus.NEW.id || requestBody.getTaskStatus() > TaskStatus.CANCELLED.id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status inválido!");
        }

        if(task.isPresent()) {
            
            task.get().setTaskStatus(requestBody.getTaskStatus());
            taskRepository.save(task.get());

            HashMap<String, Object> result = new  HashMap<String, Object> ();
            result.put("result", task);
            return result;                
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");

    }

    public HashMap<String, Object> updateTaskDescription(UpdateTaskDescription requestBody) {

        Optional<Task> task = taskRepository.findById(requestBody.getId());

        if(requestBody.getDescription() == null){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insira valor não nulo!");
        }

        if(requestBody.getDescription().length() > 250) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A descrição deve conter até 250 caracteres!");
        }
        
        if(task.isPresent()){
            //Checking the new Name
            if(requestBody.getDescription().equals(task.get().getDescription()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insira uma nova descrição!");

            if(requestBody.getDescription().equals(""))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Descrição em branco!");
            
            task.get().setName(requestBody.getDescription());
            taskRepository.save(task.get());

            HashMap<String, Object> result = new  HashMap<String, Object> ();
            result.put("result", task);
            return result;                
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");

    }

    public HashMap<String, Object> updateDeadlineOfTask(UpdateDeadlineOfTaskRequest requestBody) {
        
        Optional<Task> task = taskRepository.findById(requestBody.getId());

        if(task.isPresent()){
            
            try {
                if(!isValidDate(requestBody.getDay(), requestBody.getMonth(), requestBody.getYear()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data inválida!");
            }
            catch (Error e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valor nulo aplicado");
            }

            String deadline = requestBody.getYear() +  "-" + requestBody.getMonth() + "-" + requestBody.getDay() + " 23:59:59.000000000";

            Timestamp today = new Timestamp(new Date().getTime());

            //Chek if the deadline is before today
            if(Timestamp.valueOf(deadline).before(today)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data inválida!");
            }

            //Check if is the same date of before
            if(task.get().getDeadline().equals(Timestamp.valueOf(deadline))){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data já cadastrada");
            }


            task.get().setDeadline(Timestamp.valueOf(deadline));
            taskRepository.save(task.get());

            HashMap<String, Object> result = new  HashMap<String, Object> ();
            result.put("result", task);
            return result;

        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if(task.isPresent()){
            return task.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
    }

    public List<Task> getTaskByStatus(Integer taskStatus) {
        List<Task> task = taskRepository.findByTaskStatus(taskStatus);
        if(task.size() > 0){
            return task;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa(s) não encontrada(s)");
    }

    public HashMap<String, Object> deleteTaskById(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        HashMap<String, Object> result = new  HashMap<String, Object> ();
        if(task.isPresent()){
            taskRepository.delete(task.get());
            result.put("result", "Tarefa " + taskId + " apagada com sucesso");
            return result;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
    }

    public HashMap<String, Object> getPaginatedTasks(int page, int pageSize) {

		Order sort = new Order(Direction.ASC, "id");

        List<Task> taskList = taskRepository.findAll();
        int size = page;

        if(pageSize > taskList.size()) {
            size = taskList.size();
        }

        try {

            Page<Task> tasks = taskRepository.findAll(PageRequest.of(size, pageSize, Sort.by(sort)));

            HashMap<String, Object> result = new HashMap<String, Object>();
            result.put("tasks", tasks.getContent());
            result.put("totalOfPages", tasks.getTotalPages());
            result.put("totalOfItems", tasks.getTotalElements());
            return result;

        } catch (Error e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Paginação inválida");
        }

	}

    public boolean isValidDate(int requestDay, int requestMonth, int requestYear) {
    
        Calendar cal = Calendar.getInstance();
        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int month = cal.getActualMaximum(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR) + 1; //limited by 2 years
        
        if(requestDay > days || requestDay < 1)
            return false;
        if(requestMonth > month || requestMonth < 1)
            return false;
        if(requestYear > year || requestYear < (year - 1))
            return false;
            
        return true;
    }

}
