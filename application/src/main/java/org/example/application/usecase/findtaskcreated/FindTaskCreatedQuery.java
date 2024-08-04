package org.example.application.usecase.findtaskcreated;


import org.example.application.cqrs.Query;

import java.util.Date;

public record FindTaskCreatedQuery(int page, int size) implements Query<FoundTaskCreated> {


}
