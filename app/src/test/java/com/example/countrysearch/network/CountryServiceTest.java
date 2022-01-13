package com.example.countrysearch.network;


import com.example.countrysearch.MockResponseFileReader;
import com.example.countrysearch.data.Constants;
import com.example.countrysearch.data.dao.SearchDao;
import com.example.countrysearch.data.repo.SearchRepoImpl;
import com.example.countrysearch.network.service.CountrySearchService;
import com.google.gson.GsonBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@RunWith(MockitoJUnitRunner.class)
public class CountryServiceTest {

    private MockWebServer mockWebServer = new MockWebServer();


    //todo use hilt to inject dependencies for the testing
    @Mock
    private SearchRepoImpl searchRepo;

    @Mock
    SearchDao searchDao;

    @Mock
    private CountrySearchService countrySearchService;

    private String successResponsePath = "src/test/java/com/example/countrysearch/resources/country_success.json";

    private int MOCK_SERVER_PORT = 8000;


    @Before
    public void setUp() throws IOException {
        mockWebServer.start(MOCK_SERVER_PORT);

        countrySearchService = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation()
                        .create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.API_BASE_URL)
                .client(new OkHttpClient.Builder().build())
                .build().create(CountrySearchService.class);

        searchRepo = new SearchRepoImpl(countrySearchService,searchDao);
    }

    @After
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void name() {
        String query = "byt";

        mockWebServer.enqueue(new MockResponse()
                .setBody(MockResponseFileReader.getContent(successResponsePath)));

        searchRepo.searchForCountry(query)
                .test()
                .assertSubscribed();
    }
}
