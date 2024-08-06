package org.example.api.model;


import org.example.api.TaskAPI;
import org.example.api.mapper.TaskMapper;
import org.example.application.usecase.findtaskcreated.FoundTaskCreated;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class TaskResponseAssembler extends RepresentationModelAssemblerSupport<FoundTaskCreated, TaskQueriedResponse> {

	public TaskResponseAssembler() {
		super(TaskAPI.class, TaskQueriedResponse.class);
	}

	@Override
	public TaskQueriedResponse toModel(FoundTaskCreated entity) {
        return TaskMapper.INSTANCE.taskFoundToTaskQueriedResponse(entity);
	}

}
