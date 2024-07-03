package com.wsd.eCommerceBackend.utils;

public class CommonHelper {

    public static String formatMSISDN(String msisdn) {
        // Check if the MSISDN starts with a plus sign and has a total of 13 digits
        if (msisdn.matches("^\\+\\d{13}$")) {
            return msisdn; // MSISDN is already in the correct format
        } else {
            // If not, append "+88" and return the formatted MSISDN
            return "+88" + msisdn;
        }
    }
}
