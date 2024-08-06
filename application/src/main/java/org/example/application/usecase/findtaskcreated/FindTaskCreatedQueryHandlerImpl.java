package org.example.application.usecase.findtaskcreated;


import org.example.application.mapper.TaskMapper;
import org.example.domain.model.DomainList;
import org.example.domain.model.PageData;
import org.example.domain.model.taskcreateddomain.TaskCreatedDomain;
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
        PageData pageData = new PageData(query.size(), query.page());

        DomainList<TaskCreatedDomain> taskList = taskCreatedDomainPersistencePort.find(pageData);

        List<FoundTaskCreated> result = taskList.getLista().stream().map(TaskMapper.INSTANCE::taskDomainToFoundTask).toList();

        return new FoundTaskList(result, taskList.getTotal());
    }

}
