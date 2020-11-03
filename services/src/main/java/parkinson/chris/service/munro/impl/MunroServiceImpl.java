package parkinson.chris.service.munro.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parkinson.chris.model.Munro;
import parkinson.chris.model.MunroResult;
import parkinson.chris.model.MunroSearchCriteria;
import parkinson.chris.utils.MunroCSVReader;

import java.parkinson.chris.service.munro.MunroService;
import java.util.ArrayList;
import java.util.List;

@Service
public class MunroServiceImpl implements MunroService {

    private MunroCSVReader munroCSVReader;

    @Override
    public List<MunroResult> retrieveMunroData(MunroSearchCriteria searchCriteria) {
        List<Munro> munros = munroCSVReader.readMunrosFromCSV();
        List<MunroResult> results = mapMunrosToResult(munros);
        return null;
    }

    private List<MunroResult> mapMunrosToResult(List<Munro> munros) {
        List<MunroResult> returnValue = new ArrayList<>();
        for (Munro munro :munros) {
            MunroResult result = new MunroResult(munro.getName(), munro.getHeightMeters().toString(),
                    munro.getClassificationPost1997().name() , munro.getGridRef());
            returnValue.add(result);
        }
        return returnValue;
    }

    @Autowired
    public void setMunroCSVReader(MunroCSVReader munroCSVReader) {
        this.munroCSVReader = munroCSVReader;
    }
}
