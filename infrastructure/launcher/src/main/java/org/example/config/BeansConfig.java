package org.example.config;

import org.example.api.helper.UuidGeneratorHelper;
import org.example.application.usecase.createtask.CreateTaskCommandHandler;
import org.example.application.usecase.createtask.CreateTaskCommandHandlerImpl;
import org.example.application.usecase.findtaskcreated.FindTaskCreatedQueryHandler;
import org.example.application.usecase.findtaskcreated.FindTaskCreatedQueryHandlerImpl;
import org.example.domain.model.taskcreateddomain.TaskReadPort;
import org.example.domain.model.taskdomain.TaskWritePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.example.task-service")
public class BeansConfig {

	@Bean
	CreateTaskCommandHandler createTaskCommandHandler(TaskWritePort outputPort) {
		return new CreateTaskCommandHandlerImpl(outputPort);
	}

	@Bean
	FindTaskCreatedQueryHandler findTaskCreatedQueryHandler(TaskReadPort outputPort) {
		return new FindTaskCreatedQueryHandlerImpl(outputPort);
	}

	@Bean
	UuidGeneratorHelper uuidGenerator() {
		return UuidGeneratorHelper.random();
	}
}
