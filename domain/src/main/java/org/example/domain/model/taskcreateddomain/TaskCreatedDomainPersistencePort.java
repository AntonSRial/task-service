package org.example.domain.model.taskcreateddomain;


import java.util.Date;
import java.util.List;

public interface TaskCreatedDomainPersistencePort {

    List<TaskCreatedDomain> find(String productId, String brandId, Date applicationDate);

}
