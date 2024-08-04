package org.example.application.usecase.findtaskcreated;

import java.util.Date;

public record FoundTaskCreated(String brandId, Date startDate, Date endDate, String priceList, String productId, int priority, double price, String curr) {
}
