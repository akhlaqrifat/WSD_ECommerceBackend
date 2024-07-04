package com.wsd.eCommerceBackend.constants;

public class AppRoutes {

    public static class AuthController {
        public static final String ROOT_URL = "/api/auth";
        public static final String SIGN_IN = "/sign-in";
        public static final String REFRESH_TOKEN = "/refresh-token";
        public static final String SIGN_UP = "/sign-up";
        public static final String LOGOUT = "/logout";
        public static final String GET_CURRENT_USER = "/get-current-user";
    }

    public static class SaleController {
        public static final String ROOT_URL = "/api/sale";
        public static final String GET_ALL_SALES = "/get-all-sales";
        public static final String GET_WISHLIST_BY_CUSTOMER_ID = "/get-wishlist-by-customer-id";
        public static final String GET_TOTAL_SALES_TODAY = "/get-total-sales-today";
        public static final String GET_MAX_SALE_DAY = "/get-max-sale-day";
        public static final String GET_TOP_SELLING_PRODUCTS_ALL_TIME = "/get-top-selling-products-all-time";
        public static final String GET_TOP_SELLING_PRODUCTS_LAST_MONTH = "/get-selling-products-last-month";
        public static final String ADD_NEW_SALE = "/add-new-sale";
    }
}
