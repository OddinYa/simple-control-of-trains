package ru.serjir.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@Table(name = "stations")
@EqualsAndHashCode
@AllArgsConstructor
public class Station {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;


    @JsonIgnoreProperties("stations")
    @ManyToMany(mappedBy = "stations")
    private List<Road> roads;

    public Station() {
    }


}
