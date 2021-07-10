package com.example.countrysearch.util;

public class ValidatorUtil {

    public static boolean validateSearchQuery(String query) {
        return query != null && !(query.isEmpty());
    }
}
