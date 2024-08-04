package org.example.application.usecase.createtask;


import org.example.application.cqrs.Command;

import java.util.Date;

public record CreateTaskCommand(String brandId, Date startDate, Date enDate, String priceList, String productId, int priority, double price, String curr) implements Command {}
