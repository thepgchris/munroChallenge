package parkinson.chris.service.munro.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parkinson.chris.model.Munro;
import parkinson.chris.model.MunroResult;
import parkinson.chris.model.MunroSearchCriteria;
import parkinson.chris.model.SortPriority;
import parkinson.chris.utils.MunroCSVReader;

import parkinson.chris.service.munro.MunroService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MunroServiceImpl implements MunroService {

    private MunroCSVReader munroCSVReader;

    @Override
    public List<MunroResult> retrieveMunroData(MunroSearchCriteria searchCriteria) {
        List<Munro> munros = munroCSVReader.readMunrosFromCSV();
        if(searchCriteria.getSortColumn() != null){
            sortMunros(munros, searchCriteria);
        }



        List<Munro> filteredMunros = munros;
        if(searchCriteria.getMinimumHeight() != null){
            filteredMunros = filteredMunros.stream()
                    .filter(munro -> munro.getHeightMeters() > searchCriteria.getMinimumHeight()).collect(Collectors.toList());
        }

        if(searchCriteria.getMaximumHeight() != null){
            filteredMunros = filteredMunros.stream()
                    .filter(munro -> munro.getHeightMeters() < searchCriteria.getMaximumHeight()).collect(Collectors.toList());
        }

        if (searchCriteria.getHillCategory() != null){
            filteredMunros = filteredMunros.stream()
                    .filter(munro -> munro.getClassificationPost1997().equals(searchCriteria.getHillCategory()))
                    .collect(Collectors.toList());
        }

        if (searchCriteria.getMaximumResults() != null){
            filteredMunros = filteredMunros.stream().limit(searchCriteria.getMaximumResults()).collect(Collectors.toList());
        }

        List<MunroResult> results = mapMunrosToResult(filteredMunros);
        return results;
    }

    private void sortMunros(List<Munro> munros, MunroSearchCriteria searchCriteria) {
        switch (searchCriteria.getSortColumn()){
            case NAME:
                munros.sort(Comparator.comparing(Munro::getName));
                if(searchCriteria.getSortPriority() != null && searchCriteria.getSortPriority().equals(SortPriority.DESC)){
                    Collections.reverse(munros);
                }
                break;
            case HEIGHT:
                munros.sort(Comparator.comparing(Munro::getHeightMeters));
                if(searchCriteria.getSortPriority() != null && searchCriteria.getSortPriority().equals(SortPriority.DESC)){
                    Collections.reverse(munros);
                }
                break;
        }
    }

    private List<MunroResult> mapMunrosToResult(List<Munro> munros) {
        List<MunroResult> returnValue = new ArrayList<>();
        for (Munro munro :munros) {
            String munroHeight = null;
            if(munro.getHeightMeters() != null){
                munroHeight = munro.getHeightMeters().toString();
            }
            String munroCategory = null;
            if(munro.getClassificationPost1997() != null){
                munroCategory = munro.getClassificationPost1997().name();
            }
            MunroResult result = new MunroResult(munro.getName(),munroHeight,
                    munroCategory, munro.getGridRef());
            returnValue.add(result);
        }
        return returnValue;
    }

    @Autowired
    public void setMunroCSVReader(MunroCSVReader munroCSVReader) {
        this.munroCSVReader = munroCSVReader;
    }
}
