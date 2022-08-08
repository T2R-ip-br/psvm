package com.sukhoev.psms.hardware.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "type_current")
public class TypeCurrent {

    @Id
    @SequenceGenerator(
            name = "type_current_sequence",
            sequenceName = "type_current_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "type_current_sequence"
    )
    private Long id;
    private String typeCurrent;


    public TypeCurrent(String typeCurrent) {
        this.typeCurrent = typeCurrent;
    }
}
