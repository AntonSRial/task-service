package org.example.ddbb;

import org.example.domain.model.taskcreateddomain.TaskCreatedDomain;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskCreatedDomainMapper {

    TaskCreatedDomainMapper INSTANCE = Mappers.getMapper(TaskCreatedDomainMapper.class);

    TaskCreatedDomain taskEntityToQueryTaskDomain(TaskEntity TaskEntity);

    TaskEntity queryTaskDomainToTaskEntity(TaskCreatedDomain existingPrice);

    List<TaskCreatedDomain> taskEntityListToQueryTaskDomainList(List<TaskEntity> taskEntityList);

    List<TaskEntity> queryTaskDomainListToTaskEntityList(List<TaskCreatedDomain> existingPriceList);
}
