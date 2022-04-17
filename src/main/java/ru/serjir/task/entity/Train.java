package ru.serjir.task.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Builder
@Getter
@Setter
@Entity
@Table(name = "Train")
@EqualsAndHashCode
@AllArgsConstructor
@ToString
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

    public Train() {

    }



}


