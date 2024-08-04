package org.example.application.usecase.findtaskcreated;

import java.util.List;

public record FoundTaskList(List<FoundTaskCreated> foundTaskCreatedList) {
}
