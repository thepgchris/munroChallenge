package parkinson.chris.service.munro.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import parkinson.chris.model.*;
import parkinson.chris.utils.MunroCSVReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MunroServiceImplTest {

    @Mock
    private MunroCSVReader mockCsvReader;

    @InjectMocks
    private MunroServiceImpl testee;
    private List<Munro> bigListMunros;

    @Before
    public void setup(){
        testee = new MunroServiceImpl();
        testee.setMunroCSVReader(mockCsvReader);
        Munro hillOne = new Munro();
        hillOne.setName("HillOne");
        hillOne.setHeightMeters(10d);
        hillOne.setClassificationPost1997(MunroClassification.MUN);
        Munro hillTwo = new Munro();
        hillTwo.setName("HillTwo");
        hillTwo.setHeightMeters(15d);
        hillTwo.setClassificationPost1997(MunroClassification.MUN);
        Munro hillThree = new Munro();
        hillThree.setName("HillThree");
        hillThree.setHeightMeters(20d);
        hillThree.setClassificationPost1997(MunroClassification.TOP);
        bigListMunros = new ArrayList<>(Arrays.asList(hillOne,hillTwo,hillThree));
    }

    @Test
    public void testAllMunrosBroughtBackWithNoCriteria(){
        when(mockCsvReader.readMunrosFromCSV()).thenReturn(bigListMunros);
        MunroSearchCriteria searchCriteria = new MunroSearchCriteria();
        List<MunroResult> results = testee.retrieveMunroData(searchCriteria);
        assertFalse(results.isEmpty());
        assertEquals(3,results.size());
    }

    @Test
    public void testSortingNameWorks(){
        when(mockCsvReader.readMunrosFromCSV()).thenReturn(bigListMunros);
        MunroSearchCriteria searchCriteria = new MunroSearchCriteria();
        searchCriteria.setSortColumn(SortColumn.NAME);
        List<MunroResult> results = testee.retrieveMunroData(searchCriteria);
        MunroResult resultOne = results.get(0);
        assertEquals("HillOne", resultOne.getName());
        MunroResult resultThree = results.get(2);
        assertEquals("HillTwo", resultThree.getName());
    }

    @Test
    public void testSortingNameDescWorks(){
        when(mockCsvReader.readMunrosFromCSV()).thenReturn(bigListMunros);
        MunroSearchCriteria searchCriteria = new MunroSearchCriteria();
        searchCriteria.setSortColumn(SortColumn.NAME);
        searchCriteria.setSortPriority(SortPriority.DESC);
        List<MunroResult> results = testee.retrieveMunroData(searchCriteria);
        MunroResult resultOne = results.get(0);
        assertEquals("HillTwo", resultOne.getName());
        MunroResult resultThree = results.get(2);
        assertEquals("HillOne", resultThree.getName());
    }

    @Test
    public void testSortinHeightWorks(){
        when(mockCsvReader.readMunrosFromCSV()).thenReturn(bigListMunros);
        MunroSearchCriteria searchCriteria = new MunroSearchCriteria();
        searchCriteria.setSortColumn(SortColumn.HEIGHT);
        List<MunroResult> results = testee.retrieveMunroData(searchCriteria);
        MunroResult resultOne = results.get(0);
        assertEquals("HillOne", resultOne.getName());
        MunroResult resultThree = results.get(2);
        assertEquals("HillThree", resultThree.getName());
    }

    @Test
    public void testSortinHeightAscWorks(){
        when(mockCsvReader.readMunrosFromCSV()).thenReturn(bigListMunros);
        MunroSearchCriteria searchCriteria = new MunroSearchCriteria();
        searchCriteria.setSortColumn(SortColumn.HEIGHT);
        searchCriteria.setSortPriority(SortPriority.ASC);
        List<MunroResult> results = testee.retrieveMunroData(searchCriteria);
        MunroResult resultOne = results.get(0);
        assertEquals("HillOne", resultOne.getName());
        MunroResult resultThree = results.get(2);
        assertEquals("HillThree", resultThree.getName());
    }

    @Test
    public void testSortinHeighyDescWorks(){
        when(mockCsvReader.readMunrosFromCSV()).thenReturn(bigListMunros);
        MunroSearchCriteria searchCriteria = new MunroSearchCriteria();
        searchCriteria.setSortColumn(SortColumn.HEIGHT);
        searchCriteria.setSortPriority(SortPriority.DESC);
        List<MunroResult> results = testee.retrieveMunroData(searchCriteria);
        MunroResult resultOne = results.get(0);
        assertEquals("HillThree", resultOne.getName());
        MunroResult resultThree = results.get(2);
        assertEquals("HillOne", resultThree.getName());
    }

    @Test
    public void testFilterByMinimumHeight(){
        when(mockCsvReader.readMunrosFromCSV()).thenReturn(bigListMunros);
        MunroSearchCriteria searchCriteria = new MunroSearchCriteria();
        searchCriteria.setMinimumHeight(11d);
        List<MunroResult> results = testee.retrieveMunroData(searchCriteria);
        assertEquals(2, results.size());
        assertEquals("HillTwo", results.get(0).getName());
    }

    @Test
    public void testMaxResultsReturnsAllWhenMoreAskedForThanSupplied(){
        when(mockCsvReader.readMunrosFromCSV()).thenReturn(bigListMunros);
        MunroSearchCriteria searchCriteria = new MunroSearchCriteria();
        searchCriteria.setMaximumResults(5);
        List<MunroResult> results = testee.retrieveMunroData(searchCriteria);
        assertEquals(3, results.size());
    }

    @Test
    public void testMaxResultsLimitsReturnedResults(){
        when(mockCsvReader.readMunrosFromCSV()).thenReturn(bigListMunros);
        MunroSearchCriteria searchCriteria = new MunroSearchCriteria();
        searchCriteria.setMaximumResults(2);
        List<MunroResult> results = testee.retrieveMunroData(searchCriteria);
        assertEquals(2, results.size());
    }

    @Test
    public void testMaxHeightLimitsResults(){
        when(mockCsvReader.readMunrosFromCSV()).thenReturn(bigListMunros);
        MunroSearchCriteria searchCriteria = new MunroSearchCriteria();
        searchCriteria.setMaximumHeight(19d);
        List<MunroResult> results = testee.retrieveMunroData(searchCriteria);
        assertEquals(2, results.size());
    }

    @Test
    public void testMinHeightLimitsResults(){
        when(mockCsvReader.readMunrosFromCSV()).thenReturn(bigListMunros);
        MunroSearchCriteria searchCriteria = new MunroSearchCriteria();
        searchCriteria.setMinimumHeight(11d);
        List<MunroResult> results = testee.retrieveMunroData(searchCriteria);
        assertEquals(2, results.size());
    }

    @Test
    public void testMinAndMaxHeightLimitsResults(){
        when(mockCsvReader.readMunrosFromCSV()).thenReturn(bigListMunros);
        MunroSearchCriteria searchCriteria = new MunroSearchCriteria();
        searchCriteria.setMinimumHeight(11d);
        searchCriteria.setMaximumHeight(19d);
        List<MunroResult> results = testee.retrieveMunroData(searchCriteria);
        assertEquals(1, results.size());
    }

    @Test
    public void testHillCategoryLimitsResults(){
        when(mockCsvReader.readMunrosFromCSV()).thenReturn(bigListMunros);
        MunroSearchCriteria searchCriteria = new MunroSearchCriteria();
        searchCriteria.setHillCategory(MunroClassification.TOP);
        List<MunroResult> results = testee.retrieveMunroData(searchCriteria);
        assertEquals(1, results.size());
    }

}