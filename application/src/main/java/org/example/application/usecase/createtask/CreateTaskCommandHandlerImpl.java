package org.example.application.usecase.createtask;


import org.example.domain.model.taskdomain.TaskDomain;
import org.example.domain.model.taskdomain.TaskDomainPersistencePort;

public class CreateTaskCommandHandlerImpl implements CreateTaskCommandHandler {

    private final TaskDomainPersistencePort taskPersistencePort;


    public CreateTaskCommandHandlerImpl(TaskDomainPersistencePort outputPort) {
        this.taskPersistencePort = outputPort;
    }

    @Override
    public void handle(CreateTaskCommand command) {
        TaskDomain taskDomain = new TaskDomain(command.title(), command.dueDate(), command.description(), command.tags(), null);
        taskPersistencePort.save(taskDomain);
    }

}
