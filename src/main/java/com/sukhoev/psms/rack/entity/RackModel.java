package com.sukhoev.psms.rack.entity;

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
@Entity(name = "rack_model")
public class RackModel {

    @Id
    @SequenceGenerator(
            name = "rack_model_sequence",
            sequenceName = "rack_model_sequence",
            initialValue = 10,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rack_model_sequence"
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

    @OneToMany(
            mappedBy = "rackModel",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST},
            fetch = FetchType.LAZY
    )
    private List<Rack> racks = new ArrayList<>();

    public RackModel(String nameModelRack,
                     Integer unitHeight,
                     Integer equipmentWidth,
                     Integer usableDepth,
                     String doorType,
                     Integer height,
                     Integer width,
                     Integer depth,
                     String manufacturer,
                     Integer maximumLoad
    ) {
        this.nameModelRack = nameModelRack;
        this.unitHeight = unitHeight;
        this.equipmentWidth = equipmentWidth;
        this.usableDepth = usableDepth;
        this.doorType = doorType;
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.manufacturer = manufacturer;
        this.maximumLoad = maximumLoad;
    }
}
