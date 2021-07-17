package com.example.countrysearch.ui.search;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.countrysearch.LiveDataTestUtil;
import com.example.countrysearch.data.Resource;
import com.example.countrysearch.data.model.Country;
import com.example.countrysearch.data.repo.SearchRepoImpl;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Single;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private SearchViewModel searchViewModel;

    private String moduleName;

    @Mock
    SearchRepoImpl searchRepo;

    @Before
    public void setUp() {

        searchViewModel = new SearchViewModel(searchRepo, moduleName);
    }


    @Test
    public void valid_search_query_return_success_status_and_non_null_data_with_size_greater_than_0() throws InterruptedException {
        String query = "nigeria";

        List<Country> countryList = getCountryList();

        when(searchRepo.searchForCountry(query)).thenReturn(Single.just(countryList));

        searchViewModel.searchForCountry(query);

        verify(searchRepo).searchForCountry(query);

        Resource<List<Country>> resource = LiveDataTestUtil.getOrAwaitValue(searchViewModel.observeCountries());

        assertEquals(Resource.Status.SUCCESS, resource.status);

        assertNotNull(resource.data);

        assertTrue(resource.data.size() > 0);
    }

    private List<Country> getCountryList() {
        List<Country> countries = new ArrayList<>();

        countries.add(null);

        countries.add(null);

        return countries;
    }

    @Test
    public void invalid_search_query_returns_error_status_and_null_data() throws InterruptedException {
        String query = "byt";

        when(searchRepo.searchForCountry(query)).thenReturn(Single.just(Collections.emptyList()));

        searchViewModel.searchForCountry(query);

        verify(searchRepo).searchForCountry(query);

        Resource<List<Country>> resource = LiveDataTestUtil.getOrAwaitValue(searchViewModel.observeCountries());

        assertEquals(Resource.Status.ERROR, resource.status);

        assertNull( resource.data);

    }

}