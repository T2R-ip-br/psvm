package com.sukhoev.psms.rack;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "rack")
public class Rack {

    @Id
    @SequenceGenerator(
            name = "rack_sequence",
            sequenceName = "rack_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rack_sequence"
    )
    private Long id;
    private String nameRack;
    // id_premises
    // id_model_rack
}
