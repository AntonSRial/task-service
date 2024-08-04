package org.example.ddbb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    @Query("SELECT p FROM PriceEntity p " +
            "WHERE (:productId is null OR p.productId = :productId) " +
            "AND (:brandId is null OR p.brandId = :brandId) " +
            "AND (:applicationDate is null OR p.startDate <= :applicationDate AND p.enDate >= :applicationDate) " +
            "ORDER BY p.priority DESC, p.priceList ASC")
    List<TaskEntity> findApplicablePrices(@Param("productId") String productId,
                                          @Param("brandId") String brandId,
                                          @Param("applicationDate") Date applicationDate);
}
