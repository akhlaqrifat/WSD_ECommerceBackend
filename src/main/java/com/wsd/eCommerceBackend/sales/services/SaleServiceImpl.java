package com.wsd.eCommerceBackend.sales.services;

import com.wsd.eCommerceBackend.authentication.repositories.UserRepository;
import com.wsd.eCommerceBackend.exceptions.CustomException;
import com.wsd.eCommerceBackend.exceptions.DatabaseException;
import com.wsd.eCommerceBackend.product.models.entity.Product;
import com.wsd.eCommerceBackend.sales.models.dto.TopProductResponse;
import com.wsd.eCommerceBackend.sales.models.entity.Sale;
import com.wsd.eCommerceBackend.sales.models.entity.WishList;
import com.wsd.eCommerceBackend.sales.repositories.SaleRepository;
import com.wsd.eCommerceBackend.sales.repositories.WishListRepository;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final WishListRepository wishListRepository;
    private final UserRepository userRepository;

    @Override
    public Sale addSale(Sale sale) {
        try{
            return saleRepository.save(sale);
        }catch (DatabaseException de){
            throw new CustomException("Insertion failed!"+de);
        }
    }

    @Override
    public List<Sale> getAllSales() {
        try{
            return saleRepository.findAll();
        }catch (DatabaseException de){
            throw new CustomException("Fetching failed!"+de);
        }
    }

    @Override
    public WishList getWishListByCustomerId(Long customerId) {
        try{
            if (!userRepository.existsById(customerId))
                throw new CustomException("Customer not found!");

            return wishListRepository.findByCustomerId(customerId);

        }catch (DatabaseException de){
            throw new CustomException("Data fetching failed!"+de);
        }
    }

    @Override
    public Double getTotalSalesToday() {
        try {
            OffsetDateTime odt = OffsetDateTime.now();
            Instant instant = odt.toInstant();
            Date todayDate =  Date.from(instant);
            return saleRepository.getDateWiseTotalSaleAmount(todayDate);
        }catch (DatabaseException de){
            throw new CustomException("Fetching failed!"+de);
        }
    }

    @Override
    public Date getMaxSalesDay(LocalDate startDate, LocalDate endDate) {
        try {
            return saleRepository.getMaxSalesDay(startDate,endDate);
        }catch (DatabaseException de){
            throw new CustomException("Fetching failed!"+de);
        }
    }

    @Override
    public TopProductResponse getTop5ProductBySalesAllTime() {
        try {
            TopProductResponse topProductResponse = new TopProductResponse();
            List<Tuple> tuples = saleRepository.getTopProductsBySalesAllTime();
            List<String> productNames = new ArrayList<>();
            for (Tuple tuple : tuples) {
                productNames.add(tuple.get("name", String.class));
            }
            topProductResponse.setProductNames(productNames);

            return topProductResponse;
        }catch (DatabaseException de){
            throw new CustomException("Fetching failed!"+de);
        }
    }

    @Override
    public TopProductResponse getTop5ProductBySalesLastMonth() {
        try {
            OffsetDateTime offsetDateTime = OffsetDateTime.now();
            OffsetDateTime startOfTheMonth = offsetDateTime.minusMonths(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
            Date startDate = Date.from(startOfTheMonth.toInstant());
            OffsetDateTime endOfMonth = offsetDateTime.withDayOfMonth(1).minusDays(1).withHour(23).withMinute(59).withSecond(59).withNano(999999999);
            Date endDate = Date.from(endOfMonth.toInstant());
            TopProductResponse topProductResponse = new TopProductResponse();
            List<Tuple> tuples = saleRepository.getTopProductsBySalesLastMonth(startDate,endDate);
            List<String> productNames = new ArrayList<>();
            for (Tuple tuple : tuples) {
                productNames.add(tuple.get("name", String.class));
            }
            topProductResponse.setProductNames(productNames);

            return topProductResponse;
        }catch (DatabaseException de){
            throw new CustomException("Fetching failed!"+de);
        }
    }
}
