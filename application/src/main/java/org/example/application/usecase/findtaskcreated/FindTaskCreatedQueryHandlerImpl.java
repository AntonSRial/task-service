package org.example.application.usecase.findtaskcreated;


import org.example.domain.model.taskcreateddomain.TaskCreatedDomainPersistencePort;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FindTaskCreatedQueryHandlerImpl implements FindTaskCreatedQueryHandler {

    private final TaskCreatedDomainPersistencePort taskCreatedDomainPersistencePort;

    public FindTaskCreatedQueryHandlerImpl(TaskCreatedDomainPersistencePort outputPort) {
        this.taskCreatedDomainPersistencePort = outputPort;
    }

    @Override
    public FoundTaskList handle(FindTaskCreatedQuery query) {
        List<FoundTaskCreated> result  =  taskCreatedDomainPersistencePort.find(query.page(), query.size()).parallelStream().map(item -> new FoundTaskCreated(
                item.getTitle(), item.getDueDate(), item.getDescription(), item.getTags(), null)).toList();

        return new FoundTaskList(result);
    }

}
