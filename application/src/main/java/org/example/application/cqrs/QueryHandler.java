package org.example.application.cqrs;

public interface QueryHandler<V, T extends Query<V>> {

    <V> V handle(T query);
}
