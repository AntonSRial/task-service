package org.example.application.usecase.findtaskcreated;


import org.example.application.cqrs.QueryHandler;

public interface FindTaskCreatedQueryHandler extends QueryHandler<FoundTaskCreated, FindTaskCreatedQuery> {
    FoundTaskList handle(FindTaskCreatedQuery query);
}
