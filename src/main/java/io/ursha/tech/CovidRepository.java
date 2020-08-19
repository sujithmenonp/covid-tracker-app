package io.ursha.tech;

import org.springframework.data.repository.CrudRepository;
import sun.tools.jstat.Literal;

import java.util.List;

public interface CovidRepository extends CrudRepository<CovidDataModel, String> {

     List<CovidDataModel> findByCountry(String country);

}
