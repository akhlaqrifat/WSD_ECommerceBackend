package com.wsd.eCommerceBackend.sales.controllers;

import com.wsd.eCommerceBackend.constants.AppRoutes;
import com.wsd.eCommerceBackend.constants.IConstants;
import com.wsd.eCommerceBackend.models.commons.ResponseModel;
import com.wsd.eCommerceBackend.product.models.entity.Product;
import com.wsd.eCommerceBackend.sales.models.dto.TopProductResponse;
import com.wsd.eCommerceBackend.sales.models.entity.Sale;
import com.wsd.eCommerceBackend.sales.models.entity.WishList;
import com.wsd.eCommerceBackend.sales.services.SaleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(AppRoutes.SaleController.ROOT_URL)
@RequiredArgsConstructor
public class SaleController implements IConstants {

    private final SaleService saleService;

    @GetMapping(AppRoutes.SaleController.GET_WISHLIST_BY_CUSTOMER_ID)
    public ResponseEntity<ResponseModel<WishList>> getWishListByCustomerId(
            @RequestParam Long customerId
    ) {
        ResponseEntity<ResponseModel<WishList>> response = ResponseEntity.ok(convertToJSON(saleService.getWishListByCustomerId(customerId)));
        Objects.requireNonNull(response.getBody()).setMessage("Getting wishlist of a customer!");
        return response;
    }

    @PostMapping(AppRoutes.SaleController.ADD_NEW_SALE)
    public ResponseEntity<ResponseModel<Sale>> addNewSale(
            @RequestBody Sale sale
    ){
        ResponseEntity<ResponseModel<Sale>> response = ResponseEntity.ok(convertToJSON(saleService.addSale(sale)));
        Objects.requireNonNull(response.getBody()).setMessage("Adding a sale to a customer!");
        return response;
    }


    @GetMapping(AppRoutes.SaleController.GET_TOTAL_SALES_TODAY)
    public ResponseEntity<ResponseModel<Double>> getTotalSalesToday(){
        ResponseEntity<ResponseModel<Double>> response = ResponseEntity.ok(convertToJSON(saleService.getTotalSalesToday()));
        Objects.requireNonNull(response.getBody()).setMessage("Getting total sales today!");
        return response;
    }

    @GetMapping(AppRoutes.SaleController.GET_MAX_SALE_DAY)
    public ResponseEntity<ResponseModel<Date>> getMaxSaleDay(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
            ){
        ResponseEntity<ResponseModel<Date>> response = ResponseEntity.ok(convertToJSON(saleService.getMaxSalesDay(startDate,endDate)));
        Objects.requireNonNull(response.getBody()).setMessage("Getting max sale day!");
        return response;
    }

    @GetMapping(AppRoutes.SaleController.GET_TOP_SELLING_PRODUCTS_ALL_TIME)
    public ResponseEntity<ResponseModel<TopProductResponse>> getTopSellingProductsAllTime(){
        ResponseEntity<ResponseModel<TopProductResponse>> response = ResponseEntity.ok(convertToJSON(saleService.getTop5ProductBySalesAllTime()));
        Objects.requireNonNull(response.getBody()).setMessage("Getting top selling products all time!");
        return response;

    }

    @GetMapping(AppRoutes.SaleController.GET_TOP_SELLING_PRODUCTS_LAST_MONTH)
    public ResponseEntity<ResponseModel<TopProductResponse>> getTopSellingProductsLastMonth(){
        ResponseEntity<ResponseModel<TopProductResponse>> response = ResponseEntity.ok(convertToJSON(saleService.getTop5ProductBySalesLastMonth()));
        Objects.requireNonNull(response.getBody()).setMessage("Getting top selling products last month!");
        return response;

    }
}
