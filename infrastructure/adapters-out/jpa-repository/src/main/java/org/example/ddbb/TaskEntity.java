package org.example.ddbb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PRICE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity implements Serializable {

    /**
	 * 
	 */
	@Serial
    private static final long serialVersionUID = 8953615786709346205L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="BRAND_ID")
    private String brandId;

    @Column(name="START_DATE")
    private Date startDate;

    @Column(name="END_DATE")
    private Date enDate;

    @Column(name="PRICE_LIST")
    private String priceList;

    @Column(name="PRODUCT_ID")
    private String productId;

    @Column(name="PRIORITY")
    private int priority;

    @Column(name="PRICE")
    private double price;

    @Column(name="CURR")
    private String curr;

}
