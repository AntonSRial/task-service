package org.example.domain.model.taskcreateddomain;


import org.example.domain.model.DomainList;
import org.example.domain.model.PageData;

import java.util.Date;
import java.util.List;

public interface TaskCreatedDomainPersistencePort {

    DomainList<TaskCreatedDomain> find(PageData pageData);

}
