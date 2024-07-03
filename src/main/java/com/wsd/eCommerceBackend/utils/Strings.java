package com.wsd.eCommerceBackend.utils;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Scanner;

public class Strings {

    public static boolean isNullOrEmpty(String string) {
        if(string == null) return true;
        return string.trim().isEmpty();
    }

    public static String removeAllSpacesAndMakeUpper(String string) {
        string = string.trim();
        string = string.replaceAll("\\s+","");
        string = string.toUpperCase();
        return string;
    }

    public static String extractError(String string) {
        Scanner scanner = new Scanner(string);
        String str = scanner.nextLine();
        if(str.toLowerCase().startsWith("error:")) {
            str = str.substring(6);
        }
        return str;
    }

    public static OffsetDateTime getCurrentDateTime(){
        return OffsetDateTime.now();
    }

    public static LocalDateTime getCurrentLocalDateTime(){
        return LocalDateTime.now();
    }

    public static String removeUrl(String fullUrl){
        String partToRemove = "https://api.provisorr.com:8087/provisorr/api/aws/download/";
        return fullUrl.replace(partToRemove, "");
    }
}
