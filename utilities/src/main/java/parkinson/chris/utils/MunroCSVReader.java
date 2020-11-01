package parkinson.chris.utils;

import com.opencsv.CSVReader;
import parkinson.chris.model.Munro;
import parkinson.chris.model.MunroClassification;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MunroCSVReader {
    public List<Munro> readMunrosFromCSV(){
        List<Munro> munros = new ArrayList<Munro>();
        CSVReader reader = null;
        try{
            reader = new CSVReader(new FileReader("munrotab_v6.2.csv"));
            String[] line;
            while ((line = reader.readNext()) != null){
                munros.add(new Munro(Integer.getInteger(line[0]),Integer.getInteger(line[1]),line[2],line[3],line[4],
                        line[5],line[6],line[7],line[8],line[9],line[10],line[11],line[12],line[13],line[14],line[15],
                        line[16],MunroClassification.valueOf(line[17]),MunroClassification.valueOf(line[18]),
                        MunroClassification.valueOf(line[19]),MunroClassification.valueOf(line[20]),MunroClassification.valueOf(line[21]),
                        MunroClassification.valueOf(line[22]),MunroClassification.valueOf(line[23]),MunroClassification.valueOf(line[24]),
                        MunroClassification.valueOf(line[25]),MunroClassification.valueOf(line[26]),MunroClassification.valueOf(line[27]),line[28]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return munros;
    }

}
