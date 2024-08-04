package org.example.ddbb;

import org.example.domain.model.taskcreateddomain.TaskCreatedDomain;
import org.example.domain.model.taskcreateddomain.TaskCreatedDomainPersistencePort;
import org.example.domain.model.taskdomain.TaskDomain;
import org.example.domain.model.taskdomain.TaskDomainPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskDomainJpaAdapter implements TaskDomainPersistencePort, TaskCreatedDomainPersistencePort {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void save(TaskDomain task) {
        TaskEntity taskEntity = TaskDomainMapper.INSTANCE.taskDomainToTaskEntity(task);
        taskRepository.save(taskEntity);
    }

    @Override
    public List<TaskCreatedDomain> find(int page, int size) {
        List<TaskEntity> taskEntityList = taskRepository.findApplicablePrices(null, null, null);
        return TaskCreatedDomainMapper.INSTANCE.taskEntityListToQueryTaskDomainList(taskEntityList);
    }

}
