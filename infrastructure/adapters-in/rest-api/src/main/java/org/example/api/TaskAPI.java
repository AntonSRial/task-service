package org.example.api;

import io.swagger.v3.oas.annotations.Parameter;
import org.example.api.helper.UuidGeneratorHelper;
import org.example.api.mapper.TaskMapper;
import org.example.api.model.TaskCreatedResponse;
import org.example.api.model.TaskQueriedResponse;
import org.example.api.model.TaskRequest;
import org.example.api.model.TaskResponseAssembler;
import org.example.application.usecase.createtask.CreateTaskCommand;
import org.example.application.usecase.createtask.CreateTaskCommandHandler;
import org.example.application.usecase.findtaskcreated.FindTaskCreatedQuery;
import org.example.application.usecase.findtaskcreated.FindTaskCreatedQueryHandler;
import org.example.application.usecase.findtaskcreated.FoundTaskCreated;
import org.example.application.usecase.findtaskcreated.FoundTaskList;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("${api.version}/task")
public class TaskAPI {
    @Autowired
    CreateTaskCommandHandler createTaskCommandHandler;
    @Autowired
    PagedResourcesAssembler<FoundTaskCreated> pagedResourcesAssembler;
    @Autowired
    FindTaskCreatedQueryHandler findTaskCreatedQueryHandler;
    @Autowired
    private TaskResponseAssembler taskResponseAssembler;

    @Autowired
    UuidGeneratorHelper uuidGeneratorHelper;

    @PostMapping(path = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TaskCreatedResponse> create(@RequestBody TaskRequest task) {
        String taskId = uuidGeneratorHelper.newUuid().toString();

        CreateTaskCommand createTaskCommand = TaskMapper.INSTANCE.taskRequestToCreateTaskCommand(task, taskId);

        createTaskCommandHandler.handle(createTaskCommand);

        TaskCreatedResponse response = TaskMapper.INSTANCE.taskRequestToTaskResponse(task, taskId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @PageableAsQueryParam
    ResponseEntity<PagedModel<TaskQueriedResponse>> find(@Valid @Parameter(hidden = true) Pageable pageable) {

        FindTaskCreatedQuery query = new FindTaskCreatedQuery(pageable.page(), pageable.size());

        FoundTaskList taskList = findTaskCreatedQueryHandler.handle(query);

        Page<FoundTaskCreated> pagedTaskFound = new PageImpl<>(taskList.foundTaskCreatedList(),
                PageRequest.of(pageable.page(), pageable.size()), taskList.total());

        PagedModel<TaskQueriedResponse> linkedPaginatedFiles = pagedResourcesAssembler.toModel(pagedTaskFound,
                taskResponseAssembler);


        return ResponseEntity.ok(linkedPaginatedFiles);
    }


}