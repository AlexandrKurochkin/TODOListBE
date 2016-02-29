package com.event.planner.controller;

import com.event.planner.model.Task;
import com.event.planner.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Anton Shvechikov on 23.02.16.
 */
@RestController
public class TaskController implements ErrorController {

    private static final String ERROR_PAGE = "error";

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    public Task ajaxAddTask(@RequestBody Task task) {
        return taskService.save(task);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public List<Task> ajaxGetTasks() {
        return taskService.getAll();
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE)
    public void ajaxRemoveTask(@PathVariable("id") long taskId) {
        taskService.delete(taskId);
    }

    @RequestMapping(value = ERROR_PAGE)
    public ResponseEntity<String> errorResponse() {
        return new ResponseEntity<>("Attempt to call invalid API method.", HttpStatus.NOT_FOUND);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PAGE;
    }
}
