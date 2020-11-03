package parkinson.chris.web.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;
import parkinson.chris.model.MunroClassification;
import parkinson.chris.model.MunroResult;
import parkinson.chris.model.MunroSearchCriteria;
import parkinson.chris.service.munro.MunroService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MunroControllerTest {

    @Mock
    private MunroService mockMunroService;

    @InjectMocks
    private MunroController testee;

    @Before
    public void setup(){
        testee = new MunroController();
        testee.setMunroService(mockMunroService);
    }

    @Test(expected = ResponseStatusException.class)
    public void testExceptionThrownWhenZeroMaxResults(){
        MunroSearchCriteria searchCriteria = new MunroSearchCriteria();
        searchCriteria.setMaximumResults(0);
        List<MunroResult> results = testee.searchMunro(searchCriteria);
    }

    @Test(expected = ResponseStatusException.class)
    public void testExceptionThrownWhenZeroMaxHeight(){
        MunroSearchCriteria searchCriteria = new MunroSearchCriteria();
        searchCriteria.setMaximumHeight(Double.valueOf(0));
        List<MunroResult> results = testee.searchMunro(searchCriteria);
    }

    @Test(expected = ResponseStatusException.class)
    public void testExceptionThrownWhenZeroMinHeight(){
        MunroSearchCriteria searchCriteria = new MunroSearchCriteria();
        searchCriteria.setMinimumHeight(Double.valueOf(0));
        List<MunroResult> results = testee.searchMunro(searchCriteria);
    }

    @Test(expected = ResponseStatusException.class)
    public void testExceptionThrownWhenMinHeightGreaterThanMaxHeight(){
        MunroSearchCriteria searchCriteria = new MunroSearchCriteria();
        searchCriteria.setMaximumHeight(Double.valueOf(100));
        searchCriteria.setMinimumHeight(Double.valueOf(200));
        List<MunroResult> results = testee.searchMunro(searchCriteria);
    }

    @Test
    public void testEmptyResultReturnedWhenNothingReturnedFromService(){
        when(mockMunroService.retrieveMunroData(any())).thenReturn(new ArrayList<>());
        List<MunroResult> results = testee.searchMunro(null);
        assertTrue(results.isEmpty());
    }

    @Test
    public void testResultsReturnedWhenNothingReturnedFromService(){
        List<MunroResult> data = new ArrayList<>();
        data.add(new MunroResult("Ben Lomand", "974", MunroClassification.MUN.name(), "NN367029"));
        when(mockMunroService.retrieveMunroData(any())).thenReturn(data);
        List<MunroResult> results = testee.searchMunro(null);
        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
    }

}