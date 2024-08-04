package org.example.config;

import org.example.application.usecase.createtask.CreateTaskCommandHandler;
import org.example.application.usecase.createtask.CreateTaskCommandHandlerImpl;
import org.example.application.usecase.findtaskcreated.FindTaskCreatedQueryHandler;
import org.example.application.usecase.findtaskcreated.FindTaskCreatedQueryHandlerImpl;
import org.example.domain.model.taskcreateddomain.TaskCreatedDomainPersistencePort;
import org.example.domain.model.taskdomain.TaskDomainPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.example.task-service")
public class BeansConfig {

	@Bean
	CreateTaskCommandHandler getGreetingPriceService(TaskDomainPersistencePort outputPort) {
		return new CreateTaskCommandHandlerImpl(outputPort);
	}

	@Bean
	FindTaskCreatedQueryHandler getListExistingPriceService(TaskCreatedDomainPersistencePort outputPort) {
		return new FindTaskCreatedQueryHandlerImpl(outputPort);
	}

}
