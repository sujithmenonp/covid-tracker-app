package io.ursha.tech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class CovidService {

    @Autowired
    private CovidRepository covidRepository;

    public List<CovidDataModel> getAll(){
        Iterable<CovidDataModel> list = covidRepository.findAll();
        List<CovidDataModel> output = new ArrayList<>();
        for (CovidDataModel t : list) {
            output.add(t);
        }
        return output;
    }

    public List<CovidDataModel> getAllRegion(String country){
        Iterable<CovidDataModel> list = covidRepository.findByCountry(country);
        List<CovidDataModel> output = new ArrayList<>();
        for (CovidDataModel t : list) {
            output.add(t);
        }
        return output;
    }
}
