package org.example.application.usecase.findtaskcreated;


import org.example.application.mapper.TaskMapper;
import org.example.domain.model.DomainList;
import org.example.domain.model.PageData;
import org.example.domain.model.taskcreateddomain.TaskCreatedDomain;
import org.example.domain.model.taskcreateddomain.TaskReadPort;

import java.util.List;

public class FindTaskCreatedQueryHandlerImpl implements FindTaskCreatedQueryHandler {

    private final TaskReadPort taskWritePort;

    public FindTaskCreatedQueryHandlerImpl(TaskReadPort outputPort) {
        this.taskWritePort = outputPort;
    }

    @Override
    public FoundTaskList handle(FindTaskCreatedQuery query) {
        PageData pageData = new PageData(query.size(), query.page());

        DomainList<TaskCreatedDomain> taskList = taskWritePort.find(pageData);

        List<FoundTaskCreated> result = taskList.getLista().stream().map(TaskMapper.INSTANCE::taskDomainToFoundTask).toList();

        return new FoundTaskList(result, taskList.getTotal());
    }

}
