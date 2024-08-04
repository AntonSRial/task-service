package org.example.api;

import org.example.application.usecase.createtask.CreateTaskCommand;
import org.example.application.usecase.createtask.CreateTaskCommandHandler;
import org.example.application.usecase.findtaskcreated.FindTaskCreatedQuery;
import org.example.application.usecase.findtaskcreated.FindTaskCreatedQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.version}/task")
public class TaskAPI {
    @Autowired
    CreateTaskCommandHandler createTaskCommandHandler;
    @Autowired
    FindTaskCreatedQueryHandler findTaskCreatedQueryHandler;

    @PostMapping(path = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TaskCreatedResponse> create(@RequestBody TaskRequest task) {
        CreateTaskCommand createTaskCommand = new CreateTaskCommand(task.getTitle(), task.getDueDate(), task.getDescription(), task.getTags(), null);

        createTaskCommandHandler.handle(createTaskCommand);
        return new ResponseEntity<>(new TaskCreatedResponse(task.getTitle(), task.getDueDate(), task.getDescription(), task.getTags(), task.getStatus()), HttpStatus.CREATED);
    }

    @GetMapping("")
    ResponseEntity<List<TaskQueriedResponse>> list(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {

        FindTaskCreatedQuery query = new FindTaskCreatedQuery(page, size);

        return ResponseEntity.ok(findTaskCreatedQueryHandler.handle(query).foundTaskCreatedList().stream()
                .map(item -> new TaskQueriedResponse(item.title(), item.dueDate(), item.description(), item.tags(), null))
                .collect(Collectors.toList()));
    }


}