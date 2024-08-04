package org.example.application.usecase.findtaskcreated;


import org.example.domain.model.taskcreateddomain.TaskCreatedDomainPersistencePort;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FindTaskCreatedQueryHandlerImpl implements FindTaskCreatedQueryHandler {

    private final TaskCreatedDomainPersistencePort taskCreatedDomainPersistencePort;

    public FindTaskCreatedQueryHandlerImpl(TaskCreatedDomainPersistencePort outputPort) {
        this.taskCreatedDomainPersistencePort = outputPort;
    }

    @Override
    public FoundTaskList handle(FindTaskCreatedQuery query) {
        List<FoundTaskCreated> result  =  taskCreatedDomainPersistencePort.find(query.productId(), query.brandId(), query.applicationDate()).parallelStream().map(item -> new FoundTaskCreated(
                item.getBrandId(), item.getStartDate(), item.getEnDate(), item.getPriceList(), item.getProductId(), item.getPriority(), item.getPrice(), item.getCurr())).toList();

        if(query.applicationDate() == null) {
            return new FoundTaskList(result);
        }
        List<FoundTaskCreated> filteredList = result.stream()
                .filter(item -> result.stream()
                        .filter(otherItem -> Objects.equals(item.productId(), otherItem.productId()))
                        .mapToDouble(FoundTaskCreated::priority)
                        .max()
                        .orElse(-1) == item.priority())
                .collect(Collectors.toList());

        return new FoundTaskList(filteredList);
    }

}
