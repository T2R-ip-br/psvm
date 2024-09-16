package com.sukhoev.psms.rack.entity;

import com.sukhoev.psms.hardware.entity.Hardware;
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
@Entity(name = "connecting_hardware")
public class ConnectingHardware {

    @Id
    @SequenceGenerator(
            name = "connecting_hardware_sequence",
            sequenceName = "connecting_hardware_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "connecting_hardware_sequence"
    )
    private Long id;

    private String namePowerSupplyUnit;

    @ManyToOne
    @JoinColumn(
            name = "hardware_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "hardware_fk"
            )
    )
    private Hardware hardware;

    @ManyToOne
    @JoinColumn(
            name = "rack_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "rack_fk"
            )
    )
    private Rack rack;

    @OneToMany(
            mappedBy = "connectingHardware",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<RackConfiguration> rackConfigurations = new ArrayList<>();


    public ConnectingHardware(
            String namePowerSupplyUnit,
            Hardware hardware,
            Rack rack
    ) {
        this.namePowerSupplyUnit = namePowerSupplyUnit;
        this.hardware = hardware;
        this.rack = rack;
    }
}
