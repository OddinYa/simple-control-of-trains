package ru.serjir.task.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
//@Builder
@Getter
@Setter
@Entity
@Table(name = "Train")
@NoArgsConstructor
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @JsonIgnoreProperties("roads")
    @OneToOne
    @JoinColumn(name = "stationStart")
  private Station stationStart;

    @JsonIgnoreProperties("roads")
    @OneToOne
    @JoinColumn(name = "stationFinish")
  private Station stationFinish;

    private String info;

    //private List<Integer> Stations;
}


