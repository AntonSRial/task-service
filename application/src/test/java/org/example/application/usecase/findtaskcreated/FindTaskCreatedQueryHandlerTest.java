package org.example.application.usecase.findtaskcreated;

import org.example.domain.model.taskcreateddomain.TaskCreatedDomain;
import org.example.domain.model.taskcreateddomain.TaskCreatedDomainPersistencePort;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

public class FindTaskCreatedQueryHandlerTest {

    @Mock
    private TaskCreatedDomainPersistencePort persistencePort;

    public FindTaskCreatedQueryHandlerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void handle_ReturnsFilteredFoundPriceList_WhenQueryHasResults() {
        // Arrange
        FindTaskCreatedQuery query = new FindTaskCreatedQuery("productId", "brandId", new Date());
        TaskCreatedDomain price1 = new TaskCreatedDomain("brandId", new Date(), new Date(), "priceList1", "productId1", 0, 34, "");
        TaskCreatedDomain price2 = new TaskCreatedDomain("brandId", new Date(), new Date(), "priceList2", "productId2", 0, 24, "");
        TaskCreatedDomain price3 = new TaskCreatedDomain("brandId", new Date(), new Date(), "priceList2", "productId2", 1, 25, "");
        List<TaskCreatedDomain> queryResult = Arrays.asList(price1, price2, price3);

        when(persistencePort.find(anyString(), anyString(), any(Date.class))).thenReturn(queryResult);

        // Act
        FindTaskCreatedQueryHandlerImpl queryHandler = new FindTaskCreatedQueryHandlerImpl(persistencePort);
        FoundTaskList foundTaskList = queryHandler.handle(query);

        // Assert
        assertEquals(2, foundTaskList.foundTaskCreatedList().size());
        assertEquals("priceList1", foundTaskList.foundTaskCreatedList().get(0).priceList());
        assertEquals("priceList2", foundTaskList.foundTaskCreatedList().get(1).priceList());
        assertEquals(25, foundTaskList.foundTaskCreatedList().get(1).price());

    }

    @Test
    public void handle_ReturnsEmptyFoundPriceList_WhenQueryHasNoResults() {
        // Arrange
        FindTaskCreatedQuery query = new FindTaskCreatedQuery("productId", "brandId", new Date());
        when(persistencePort.find(anyString(), anyString(), any(Date.class))).thenReturn(Collections.emptyList());

        // Act
        FindTaskCreatedQueryHandlerImpl queryHandler = new FindTaskCreatedQueryHandlerImpl(persistencePort);
        FoundTaskList foundTaskList = queryHandler.handle(query);

        // Assert
        assertEquals(0, foundTaskList.foundTaskCreatedList().size());
    }
}
