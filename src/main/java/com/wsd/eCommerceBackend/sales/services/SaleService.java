package com.wsd.eCommerceBackend.sales.services;

import com.wsd.eCommerceBackend.product.models.entity.Product;
import com.wsd.eCommerceBackend.sales.models.dto.TopProductResponse;
import com.wsd.eCommerceBackend.sales.models.entity.Sale;
import com.wsd.eCommerceBackend.sales.models.entity.WishList;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

public interface SaleService {
    Sale addSale(Sale sale);
    List<Sale> getAllSales();
    WishList getWishListByCustomerId(Long customerId);
    Double getTotalSalesToday();
    Date getMaxSalesDay(LocalDate startDate, LocalDate endDate);
    TopProductResponse getTop5ProductBySalesAllTime();
    TopProductResponse getTop5ProductBySalesLastMonth();
}
