package org.example.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {
    private String brandId;

    private Date startDate;

    private Date enDate;

    private String priceList;

    private String productId;

    private int priority;

    private double price;

    private String curr;

}
