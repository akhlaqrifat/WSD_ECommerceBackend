package com.wsd.eCommerceBackend.sales.repositories;

import com.wsd.eCommerceBackend.product.models.entity.Product;
import com.wsd.eCommerceBackend.sales.models.entity.Sale;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT COALESCE(SUM(s.paidAmount), 0.0) FROM Sale s WHERE DATE(s.createdOn) = DATE(:today)")
    Double getDateWiseTotalSaleAmount(@Param("today") Date date);

    @Query(value = "SELECT " +
            "    p.name, " +
            "    SUM(p.price) * COUNT(sp.product_id) AS total_sale_amount " +
            "FROM sale_product sp " +
            "JOIN product p ON sp.product_id = p.id " +
            "GROUP BY sp.product_id, p.name " +
            "ORDER BY total_sale_amount DESC " +
            "LIMIT 5 " , nativeQuery = true)
    List<Tuple> getTopProductsBySalesAllTime();

    @Query(value = "select count(product_id), p.name " +
            "from sale_product " +
            "join product p on p.id = sale_product.product_id " +
            "join sale s on sale_product.sale_id = s.id " +
            "where s.created_on between :startDate and :endDate " +
            "group by sale_product.product_id, p.name " +
            "order by count(product_id) desc limit 5 " , nativeQuery = true)
    List<Tuple> getTopProductsBySalesLastMonth(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
            );

    @Query(value = "select s.created_on , sum(s.paid_amount) " +
            "from sale s " +
            "where created_on between :startDate and :endDate " +
            "group by s.created_on " +
            "order by sum(s.paid_amount) " +
            "DESC LIMIT 1", nativeQuery = true)
    Date getMaxSalesDay(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);
}
