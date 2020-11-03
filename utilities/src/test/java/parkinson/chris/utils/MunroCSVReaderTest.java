package parkinson.chris.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import parkinson.chris.model.Munro;
import parkinson.chris.model.MunroClassification;

import java.util.List;

import static org.junit.Assert.*;

public class MunroCSVReaderTest {

    private MunroCSVReader testee;

    @Test
    public void testCSVisRead(){
        testee = new MunroCSVReader();
        List<Munro> result = testee.readMunrosFromCSV();
        assertNotNull(result);
        assertEquals(508,result.size());
    }

    @Test
    public void testFirstRowMappedAsExpected(){
        testee = new MunroCSVReader();
        List<Munro> munrosList = testee.readMunrosFromCSV();
        Munro result = munrosList.get(0);
        assertEquals(1, result.getRunningNo().intValue());
        assertEquals(1, result.getDoBIHNumber().intValue());
        assertEquals("http://www.streetmap.co.uk/newmap.srf?x=277324&y=730857&z=3&sv=277324,730857&st=4&tl=~&bi=~&lu=N&ar=y",result.getStreetMapUrl());
        assertEquals("http://www.geograph.org.uk/gridref/NN7732430857", result.getGeographUrl());
        assertEquals("http://www.hill-bagging.co.uk/mountaindetails.php?qu=S&rf=1", result.getHillBaggingUrl());
        assertEquals("Ben Chonzie", result.getName());
        assertEquals("1", result.getSmcSection());
        assertEquals("01A",result.getRhbSection());
        assertEquals("1.1", result.getSection());
        assertEquals(931,result.getHeightMeters().doubleValue(),0);
        assertEquals(3054, result.getHeightFeet().doubleValue(),0);
        assertEquals("51 52", result.getMapOneFifty());
        assertEquals("OL47W 368W 379W", result.getMapOneTwentyFive());
        assertEquals("NN773308", result.getGridRef());
        assertEquals("NN7732430857",  result.getGridRefXY());
        assertEquals("277324", result.getXcoord());
        assertEquals("730857", result.getYcoord());
        assertEquals(MunroClassification.MUN, result.getClassification1891());
        assertEquals(MunroClassification.MUN , result.getClassification1921());
        assertEquals(MunroClassification.MUN, result.getClassification1933());
        assertEquals(MunroClassification.MUN, result.getClassification1953());
        assertEquals(MunroClassification.MUN, result.getClassification1969());
        assertEquals(MunroClassification.MUN, result.getClassification1974());
        assertEquals(MunroClassification.MUN, result.getClassification1981());
        assertEquals(MunroClassification.MUN, result.getClassification1984());
        assertEquals(MunroClassification.MUN, result.getClassification1990());
        assertEquals(MunroClassification.MUN, result.getClassification1997());
        assertEquals(MunroClassification.MUN, result.getClassificationPost1997());
        assertTrue(StringUtils.isEmpty(result.getComments()));
    }

}