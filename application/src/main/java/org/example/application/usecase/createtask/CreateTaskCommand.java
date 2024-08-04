package org.example.application.usecase.createtask;


import org.example.application.cqrs.Command;
import org.example.application.usecase.TaskStatus;

import java.util.Date;
import java.util.List;

public record CreateTaskCommand(String title,
                                Date dueDate,
                                String description,
                                List<String> tags,
                                TaskStatus status) implements Command {
}
