package com.example.countrysearch.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryGetApiResponse {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("topLevelDomain")
    @Expose
    public List<String> topLevelDomain = null;
    @SerializedName("alpha2Code")
    @Expose
    public String alpha2Code;
    @SerializedName("alpha3Code")
    @Expose
    public String alpha3Code;
    @SerializedName("callingCodes")
    @Expose
    public List<String> callingCodes = null;
    @SerializedName("capital")
    @Expose
    public String capital;
    @SerializedName("population")
    @Expose
    public Integer population;
    @SerializedName("latlng")
    @Expose
    public List<Double> latlng = null;
    @SerializedName("timezones")
    @Expose
    public List<String> timezones = null;
    @SerializedName("numericCode")
    @Expose
    public String numericCode;
    @SerializedName("currencies")
    @Expose
    public List<Currency> currencies = null;
    @SerializedName("flag")
    @Expose
    public String flag;
    @SerializedName("languages")
    @Expose
    public List<Language> languages = null;


    public class Language {
        @SerializedName("iso639_1")
        @Expose
        public String iso6391;
        @SerializedName("iso639_2")
        @Expose
        public String iso6392;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("nativeName")
        @Expose
        public String nativeName;
    }

    public class Currency {
        @SerializedName("code")
        @Expose
        public String code;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("symbol")
        @Expose
        public String symbol;
    }

}
