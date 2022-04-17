package ru.serjir.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        Station station = (Station) o;
//
//        return this.id.equals(station.id);
//    }


}
