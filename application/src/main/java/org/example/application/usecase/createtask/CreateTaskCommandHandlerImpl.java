package org.example.application.usecase.createtask;


import org.example.application.mapper.TaskMapper;
import org.example.domain.model.taskdomain.TaskDomain;
import org.example.domain.model.taskdomain.TaskDomainPersistencePort;

public class CreateTaskCommandHandlerImpl implements CreateTaskCommandHandler {

    private final TaskDomainPersistencePort taskPersistencePort;


    public CreateTaskCommandHandlerImpl(TaskDomainPersistencePort outputPort) {
        this.taskPersistencePort = outputPort;
    }

    @Override
    public void handle(CreateTaskCommand command) {
        TaskDomain taskDomain = TaskMapper.INSTANCE.taskCommandToTaskDomain(command);
        taskPersistencePort.save(taskDomain);
    }

}
