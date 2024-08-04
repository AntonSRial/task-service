package org.example.api;

import org.example.application.usecase.createtask.CreateTaskCommand;
import org.example.application.usecase.createtask.CreateTaskCommandHandler;
import org.example.application.usecase.findtaskcreated.FindTaskCreatedQuery;
import org.example.application.usecase.findtaskcreated.FindTaskCreatedQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.version}/price")
public class TaskAPI {
    @Autowired
    CreateTaskCommandHandler createTaskCommandHandler;
    @Autowired
    FindTaskCreatedQueryHandler findTaskCreatedQueryHandler;

    @PostMapping(path = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TaskCreatedResponse> create(@RequestBody TaskRequest price) {
        CreateTaskCommand createTaskCommand = new CreateTaskCommand(price.getBrandId(), price.getStartDate(), price.getEnDate(), price.getPriceList(), price.getProductId(), price.getPriority(), price.getPrice(), price.getCurr());

        createTaskCommandHandler.handle(createTaskCommand);
        return new ResponseEntity<>(new TaskCreatedResponse(price.getBrandId(), price.getStartDate(), price.getEnDate(), price.getPriceList(), price.getProductId(), price.getPriority(), price.getPrice(), price.getCurr()), HttpStatus.CREATED);
    }

    @GetMapping("")
    ResponseEntity<List<TaskQueriedResponse>> list(String productId, String brandId, @RequestParam(name = "applicationDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date applicationDate) {

        FindTaskCreatedQuery query = new FindTaskCreatedQuery(productId, brandId, applicationDate);

        return ResponseEntity.ok(findTaskCreatedQueryHandler.handle(query).foundTaskCreatedList().stream()
                .map(item -> new TaskQueriedResponse(item.brandId(), item.startDate(), item.endDate(), item.priceList(), item.productId(), item.price() +item.curr()))
                .collect(Collectors.toList()));
    }


}
