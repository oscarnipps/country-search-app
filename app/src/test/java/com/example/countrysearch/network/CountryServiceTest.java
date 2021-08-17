package com.example.countrysearch.network;


import com.example.countrysearch.MockResponseFileReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;


@RunWith(MockitoJUnitRunner.class)
public class CountryServiceTest {

    private MockWebServer mockWebServer = new MockWebServer();


    //todo use hilt to inject dependencies for the testing
    /*@Mock
    private SearchRepoImpl searchRepo;

    @Mock
    private CountrySearchService countrySearchService;*/

    private String successResponsePath = "src/test/java/com/example/countrysearch/resources/country_success.json";

    private int MOCK_SERVER_PORT = 8000;


    @Before
    public void setUp() throws IOException {
        mockWebServer.start(MOCK_SERVER_PORT);

        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void name() {
        String query = "byt";

        mockWebServer.enqueue(new MockResponse().setBody(MockResponseFileReader.getContent(successResponsePath)));

        /*searchRepo.searchForCountry(query)
                .test()
                .assertNoErrors();*/
    }
}
