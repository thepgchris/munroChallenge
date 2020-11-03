package parkinson.chris.web.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.server.ResponseStatusException;
import parkinson.chris.model.MunroResult;
import parkinson.chris.model.MunroSearchCriteria;

import java.util.List;

import static org.junit.Assert.*;

public class MunroControllerTest {

    private MunroController testee;

    @Before
    public void setup(){
        testee = new MunroController();
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

}