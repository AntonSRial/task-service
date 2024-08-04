package org.example.domain.model.taskcreateddomain;


import java.util.Date;
import java.util.List;

public interface TaskCreatedDomainPersistencePort {

    List<TaskCreatedDomain> find(int page, int size);

}
