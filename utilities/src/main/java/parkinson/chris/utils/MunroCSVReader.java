package parkinson.chris.utils;

import com.opencsv.CSVReader;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import parkinson.chris.model.Munro;
import parkinson.chris.model.MunroClassification;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MunroCSVReader {
    public List<Munro> readMunrosFromCSV(){
        List<Munro> munros = new ArrayList<Munro>();
        try{
            CSVReader reader = new CSVReader(new FileReader("src/main/resources/munrotab_v6.2.csv"));
            String[] line;
            int counter = 0;
            while ((line = reader.readNext()) != null){
                //Skip the headers and lines greater than the dataset to ignore totals (quick and dirty fix to bypass bottom rows of csv)
                if(counter == 0 || counter > 601 ){
                    counter++;
                    continue;
                }
                //Skip results where post 1997 classification is null - could be added to above if but separated for clarity
                if(StringUtils.isEmpty(line[27])){
                    counter++;
                    continue;
                }

                munros.add(new Munro(Integer.valueOf(line[0]),Integer.valueOf(line[1]),line[2],line[3],line[4],
                        line[5],line[6],line[7],line[8],Double.valueOf(line[9]),Double.valueOf(line[10]),line[11],line[12],line[13],line[14],line[15],
                        line[16],convertStringToMunroClassification(line[17]),convertStringToMunroClassification(line[18]),
                        convertStringToMunroClassification(line[19]),convertStringToMunroClassification(line[20]),convertStringToMunroClassification(line[21]),
                        convertStringToMunroClassification(line[22]),convertStringToMunroClassification(line[23]),convertStringToMunroClassification(line[24]),
                        convertStringToMunroClassification(line[25]),convertStringToMunroClassification(line[26]),convertStringToMunroClassification(line[27]),line[28]));
                counter++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return munros;
    }

    private MunroClassification convertStringToMunroClassification(String classification) {
        if (StringUtils.isEmpty(classification)){
            return null;
        }
        return MunroClassification.valueOf(classification);
    }

}
