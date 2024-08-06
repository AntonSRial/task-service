package org.example.application.usecase.findtaskcreated;

import org.example.application.usecase.TaskStatus;

import java.util.Date;
import java.util.List;

public record FoundTaskCreated(String id,
                               String title,
                               Date dueDate,
                               String description,
                               List<String> tags,
                               TaskStatus status) {
}
