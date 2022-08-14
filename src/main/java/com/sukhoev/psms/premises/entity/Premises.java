package com.sukhoev.psms.premises.entity;

import com.sukhoev.psms.rack.entity.Rack;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "premises")
public class Premises {

    @Id
    @SequenceGenerator(
            name = "premises_sequence",
            sequenceName = "premises_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "premises_sequence"
    )
    private Long id;
    private String city;
    private String building;
    private String room;
    private Integer square;
    private Integer rackLimit;
    private String comment;

    @OneToMany(
            mappedBy = "premises",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST},
            fetch = FetchType.LAZY
    )
    private List<Rack> racks = new ArrayList<>();

    public Premises(
            String city,
            String building,
            String room,
            Integer square,
            Integer rackLimit,
            String comment
    ) {
        this.city = city;
        this.building = building;
        this.room = room;
        this.square = square;
        this.rackLimit = rackLimit;
        this.comment = comment;
    }

    public List<Rack> getRacks() {
        return racks;
    }

    @Override
    public String toString() {
        return "Premises{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", building='" + building + '\'' +
                ", premises='" + room + '\'' +
                ", square=" + square +
                ", rackLimit=" + rackLimit +
                ", comment='" + comment +
                '}';
    }
}
