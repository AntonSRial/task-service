package org.example.application.mapper;

import org.example.application.usecase.createtask.CreateTaskCommand;
import org.example.application.usecase.findtaskcreated.FoundTaskCreated;
import org.example.domain.model.taskcreateddomain.TaskCreatedDomain;
import org.example.domain.model.taskdomain.TaskDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

	@Mapping(target = "id", source = "id")
    TaskDomain taskCommandToTaskDomain(CreateTaskCommand taskCommand);

    @Mapping(target = "id", source = "id")
    FoundTaskCreated taskDomainToFoundTask(TaskCreatedDomain taskDomain);

}
