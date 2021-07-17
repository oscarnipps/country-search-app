package com.example.countrysearch.util;

import com.example.countrysearch.data.model.Country;
import com.example.countrysearch.network.model.CountryGetApiResponse;

import java.util.ArrayList;
import java.util.List;

public class CountryMapperUtil {

    public static List<Country> getCountryListFromApiResponse(List<CountryGetApiResponse> data) {
        List<Country> countryList = new ArrayList<>();

        if (data == null) {
            return countryList;
        }

        for (CountryGetApiResponse response : data) {
            Country country = new Country(
                    response.name,
                    response.callingCodes.get(0),
                    response.capital,
                    response.population,
                    response.timezones.get(0),
                    response.languages.get(0).nativeName,
                    response.flag,
                    response.currencies.get(0).code
            );

            countryList.add(country);
        }

        return countryList;
    }
}
