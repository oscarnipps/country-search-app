package com.example.countrysearch.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorUtilTest {


    @Test()
    public void empty_search_query_returns_false() {
        String query = "";

        boolean result = ValidatorUtil.validateSearchQuery(query);

        assertFalse(result);
    }

    @Test
    public void null_search_query_returns_false() {
        String query = null;

        boolean result = ValidatorUtil.validateSearchQuery(query);

        assertFalse(result);
    }

    @Test
    public void non_empty_query_returns_true() {
        String query = "nigeria";

        boolean result = ValidatorUtil.validateSearchQuery(query);

        assertTrue(result);
    }

}