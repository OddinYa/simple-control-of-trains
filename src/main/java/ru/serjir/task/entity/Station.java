package ru.serjir.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "stations")
public class Station {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;


    @JsonIgnoreProperties("stations")
    @ManyToMany(mappedBy = "stations")
    private List<Road> roads ;




}
