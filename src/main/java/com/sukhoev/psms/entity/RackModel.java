package com.sukhoev.psms.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "rack_model")
public class RackModel {

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
    private String nameModelRack;
    private Integer unitHeight;
    private Integer equipmentWidth;
    private Integer usableDepth;
    private String doorType;
    private Integer height;
    private Integer width;
    private Integer depth;
    private String manufacturer;
    private Integer maximumLoad;



}
