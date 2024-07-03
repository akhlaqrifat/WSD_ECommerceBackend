package com.wsd.eCommerceBackend.constants;

public class AppRoutes {

    public static class AuthController {
        public static final String ROOT_URL = "/api/auth";
        public static final String SIGN_IN = "/sign-in";
        public static final String REFRESH_TOKEN = "/refresh-token";
        public static final String SIGN_UP = "/sign-up";
        public static final String CHANGE_PASSWORD = "/change-password";
        public static final String DELETE_REFRESH_TOKEN = "/deleteRefreshToken/{user_id}";
        public static final String LOGOUT = "/logout";
        public static final String GET_CURRENT_USER = "/get-current-user";
        public static final String SIGN_IN_ADMIN = "/sign-in-admin";
    }

    public static class NotificationController {
        public static final String ROOT_URL = "/api/notifications";
        public static final String ADD_TOKEN = "/addToken";
        public static final String DELETE_TOKEN = "/deleteToken";
        public static final String SEND_NOTIFICATION_SINGLE = "/sendNotificationSingle";
        public static final String SEND_NOTIFICATION_BULK = "/sendNotificationBulk";
    }

    public static class CategoryController {
        public static final String ROOT_URL = "/api/category";
        public static final String ADD_CATEGORY = "/addCategory";
        public static final String ADD_SUBCATEGORY = "/addSubcategory";
        public static final String DELETE_CATEGORY = "/deleteCategory";
        public static final String DELETE_SUBCATEGORY = "/deleteSubcategory";
        public static final String UPDATE_CATEGORY = "/updateCategory";
        public static final String UPDATE_SUBCATEGORY = "/updateSubcategory";
        public static final String SYNC_POSITION = "/syncPosition";
        public static final String GET_ALL = "/getALL";
        public static final String GET_SUBCATEGORY_BY_CATEGORY = "/getSubcategoryByCategory";
        public static final String SEARCH_ALL_CAT_SUB_CAT = "/searchCategoryByName";
    }

    public static class UtilsController {
        public static final String ROOT_URL = "/api/utils";
        public static final String ADD_BANNER = "/addBanner";
        public static final String DELETE_BANNER = "/deleteBanner";
        public static final String UPDATE_BANNER = "/updateBanner";
        public static final String GET_ALL_BANNERS = "/getAllBanners";
        public static final String SYNC_BANNER_POSITION = "/syncBannerPosition";
        public static final String ADD_DEAL = "/addDeal";
        public static final String DELETE_DEAL = "/deleteDeal";
        public static final String UPDATE_DEALS = "/updateDeal";
        public static final String GET_ALL_DEALS = "/getAllDeals";
        public static final String SYNC_DEAL_POSITION = "/syncDealPosition";
        public static final String FIND_APP_VERSION_BY_OS = "/getAppVersionByOS";
        public static final String ADD_APP_VERSION = "/addAppVersion";
        public static final String DELETE_APP_VERSION = "/deleteAppVersion";
        public static final String UPDATE_APP_VERSION = "/updateAppVersion";
    }

    public static class JobController {
        public static final String ROOT_URL = "/api/job";
        public static final String ADD_NEW_JOB = "/addJob";
        public static final String DELETE_JOB = "/deleteJob";
        public static final String UPDATE_JOB_DETAILS = "/updateJob";
        public static final String GET_ALL_JOB = "/getAllJobs";
        public static final String GET_JOB_BY_JOB_ID = "/getJobByJobId";
        public static final String GET_JOB_BY_ORDER_ID = "/getJobByOrderId";
        public static final String GET_COMPLETED_JOB = "/getCompletedJobs";
        public static final String GET_ALL_JOBS_ADMIN = "/getAllJobsAdmin";
        public static final String REMOVE_OLDER_JOBS = "/removeOlderJobs";
    }
}
