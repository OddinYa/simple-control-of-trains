package ru.serjir.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "roads")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Road {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer length;

    @JsonIgnoreProperties("roads")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "stations_has_roads",
            joinColumns = @JoinColumn(name = "roads_id"),
            inverseJoinColumns = @JoinColumn(name = "stations_id"))

    private List<Station> stations ;


}

