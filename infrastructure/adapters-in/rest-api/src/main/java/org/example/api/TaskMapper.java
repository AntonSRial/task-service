package org.example.api;

import org.example.application.usecase.createtask.CreateTaskCommand;
import org.example.application.usecase.findtaskcreated.FoundTaskCreated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

	@Mapping(target = "id", source = "id")
    CreateTaskCommand taskRequestToCreateTaskCommand(TaskRequest taskRequest, String id);

	@Mapping(target = "id", source = "id")
	TaskCreatedResponse taskRequestToTaskResponse(TaskRequest taskRequest, String id);

    TaskQueriedResponse taskFoundToTaskQueriedResponse (FoundTaskCreated foundTaskCreated);

}
