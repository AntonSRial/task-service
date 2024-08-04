package org.example.application.cqrs;

/**
 * Interface que define como debe comportarse una implementación de un bus de comandos.
 *
 * Dado un {@link Command}, un {@link CommandBus} debe buscar la lista de {@link CommandHandler}
 * que pueden procesar dicho {@link Command} y ejecutarlos.
 *
 */
public interface CommandBus {

    void handle(Command command) throws Exception;
}
