package java.parkinson.chris.service.munro;

import parkinson.chris.model.MunroResult;
import parkinson.chris.model.MunroSearchCriteria;

import java.util.List;

public interface MunroService {

    List<MunroResult> retrieveMunroData(MunroSearchCriteria searchCriteria);
}
