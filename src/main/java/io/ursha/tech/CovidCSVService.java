package io.ursha.tech;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
class CovidCSVService {

    @Autowired
    private CovidRepository covidRepository;

    public void process(String body) throws IOException {

        StringReader reader = new StringReader(body);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.
                withFirstRecordAsHeader().parse(reader);

        List<CovidDataModel> covidDataModels = new ArrayList<>();
        for(CSVRecord record: records){
            CovidDataModel covidDataModel = new CovidDataModel();

            String country = record.get("Country/Region");
            String latestCount = record.get(record.size()-1);
            String previousCount = record.get(record.size()-2);
            String state = record.get("Province/State");

            int lc =0, pc=0;
            covidDataModel.setCountry(country);
            covidDataModel.setState(state);

            if(!StringUtils.isEmpty(latestCount))
                lc = Integer.parseInt(latestCount);

            if(!StringUtils.isEmpty(previousCount))
                pc = Integer.parseInt(previousCount);

            covidDataModel.setDiffFromPrevious(lc-pc);
            covidDataModel.setLatestCount(lc);

            covidDataModels.add(covidDataModel);

        }
        covidRepository.saveAll(covidDataModels);
    }

}
