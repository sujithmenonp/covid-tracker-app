package io.ursha.tech;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
class CovidDataModel {

    @Id
    @GeneratedValue
    private int id;
    private String country;
    private String state;
    private int activeCount;
}
