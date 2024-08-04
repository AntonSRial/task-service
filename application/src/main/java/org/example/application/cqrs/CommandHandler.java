package org.example.application.cqrs;

public interface CommandHandler<T extends Command> {

    void handle(T command);
}
