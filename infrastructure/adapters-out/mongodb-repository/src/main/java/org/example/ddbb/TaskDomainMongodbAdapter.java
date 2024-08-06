package org.example.ddbb;

import org.example.domain.model.PageData;
import org.example.domain.model.DomainList;
import org.example.domain.model.taskcreateddomain.TaskCreatedDomain;
import org.example.domain.model.taskcreateddomain.TaskCreatedDomainPersistencePort;
import org.example.domain.model.taskdomain.TaskDomain;
import org.example.domain.model.taskdomain.TaskDomainPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class TaskDomainMongodbAdapter implements TaskDomainPersistencePort, TaskCreatedDomainPersistencePort {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void save(TaskDomain task) {
        TaskEntity taskEntity = TaskDomainMapper.INSTANCE.taskDomainToTaskEntity(task);
        taskRepository.save(taskEntity);
    }

    @Override
    public DomainList<TaskCreatedDomain> find(PageData pageData) {
        Page<TaskEntity> pagedTaskFound = taskRepository.findAll(PageRequest.of(pageData.page(), pageData.size(), Sort.by(Direction.DESC, "creationDate")));
        return new DomainList<>(TaskCreatedDomainMapper.INSTANCE.taskEntityListToQueryTaskDomainList(
                pagedTaskFound.getContent()), (int) pagedTaskFound.getTotalElements());
    }
}
