package org.example.application.usecase.findtaskcreated;


import org.example.application.cqrs.Query;

import java.util.Date;

public record FindTaskCreatedQuery(String productId, String brandId, Date applicationDate) implements Query<FoundTaskCreated> {


}
