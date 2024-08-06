package org.example.domain.model.taskcreateddomain;


import org.example.domain.model.DomainList;
import org.example.domain.model.PageData;

public interface TaskReadPort {

    DomainList<TaskCreatedDomain> find(PageData pageData);

}
