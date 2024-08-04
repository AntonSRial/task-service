package org.example.ddbb;

import org.example.domain.model.taskdomain.TaskDomain;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskDomainMapper {

    TaskDomainMapper INSTANCE = Mappers.getMapper(TaskDomainMapper.class);

    TaskDomain taskEntityToTaskDomain(TaskEntity taskEntity);

    TaskEntity taskDomainToTaskEntity(TaskDomain price);

    List<TaskDomain> taskEntityListToTaskDomainList(List<TaskEntity> taskEntityList);

    List<TaskEntity> taskDomainListToTaskEntityList(List<TaskDomain> taskDomainList);
}
