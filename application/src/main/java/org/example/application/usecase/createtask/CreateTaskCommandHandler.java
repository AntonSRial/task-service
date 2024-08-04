package org.example.application.usecase.createtask;


import org.example.application.cqrs.CommandHandler;

public interface CreateTaskCommandHandler extends CommandHandler<CreateTaskCommand> {

    void handle(CreateTaskCommand command);

}
