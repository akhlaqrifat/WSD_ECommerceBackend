package com.wsd.eCommerceBackend.sales.services;

import com.wsd.eCommerceBackend.authentication.repositories.UserRepository;
import com.wsd.eCommerceBackend.exceptions.CustomException;
import com.wsd.eCommerceBackend.exceptions.DatabaseException;
import com.wsd.eCommerceBackend.product.models.entity.Product;
import com.wsd.eCommerceBackend.sales.models.entity.Sale;
import com.wsd.eCommerceBackend.sales.models.entity.WishList;
import com.wsd.eCommerceBackend.sales.repositories.SaleRepository;
import com.wsd.eCommerceBackend.sales.repositories.WishListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        return 0.0;
    }

    @Override
    public Date getMaxSalesDay(Date startDate, Date endDate) {
        return null;
    }

    @Override
    public List<Product> getTop5ProductBySalesAllTime() {
        return List.of();
    }

    @Override
    public List<Product> getTop5ProductBySalesLastMonth() {
        return List.of();
    }
}
