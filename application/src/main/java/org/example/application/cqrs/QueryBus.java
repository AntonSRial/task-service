package org.example.application.cqrs;

/**
 * Interface que define como debe comportarse una implementación de un bus de queries.
 *
 * Dado un {@link Query}, un {@link QueryBus} debe buscar la lista de {@link QueryHandler}
 * que pueden procesar dicho {@link Query} y devolver el resultado.
 *
 */
public interface QueryBus {

    void handle(Query query) throws Exception;
}
