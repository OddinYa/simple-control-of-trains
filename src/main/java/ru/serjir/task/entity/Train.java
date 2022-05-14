package ru.serjir.task.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@Table(name = "Train")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
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
    @Transient
    private String info;


}


