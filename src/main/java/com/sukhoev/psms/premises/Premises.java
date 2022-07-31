package com.sukhoev.psms.premises;

import com.sukhoev.psms.rack.Racks;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String premises;
    private Integer square;
    private Integer rackLimit;
    private String comment;

    @OneToMany(
            mappedBy = "premises",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Racks> racks = new ArrayList<>();

    public Premises(
            String city,
            String building,
            String premises,
            Integer square,
            Integer rackLimit,
            String comment
    ) {
        this.city = city;
        this.building = building;
        this.premises = premises;
        this.square = square;
        this.rackLimit = rackLimit;
        this.comment = comment;
    }
}
