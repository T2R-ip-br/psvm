package com.sukhoev.psms.rack.entity;

import com.sukhoev.psms.hardware.entity.Hardware;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "rack_configuration")
public class RackConfiguration {

    @Id
    @SequenceGenerator(
            name = "rack_configuration_sequence",
            sequenceName = "rack_configuration_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rack_configuration_sequence"
    )
    private Long id;

    private String nameHardware;
    private Integer occupiedUnit;
    private Integer powerCableLength;
    private Integer powerCableCrossSection;

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

    @ManyToOne
    @JoinColumn(
            name = "connecting_hardware_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "connecting_hardware_fk"
            )
    )
    private ConnectingHardware connectingHardware;

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


    public RackConfiguration(
            String nameHardware,
            Integer occupiedUnit,
            Integer powerCableLength,
            Integer powerCableCrossSection,
            Rack rack,
            ConnectingHardware connectingHardware,
            Hardware hardware
    ) {
        this.nameHardware = nameHardware;
        this.occupiedUnit = occupiedUnit;
        this.powerCableLength = powerCableLength;
        this.powerCableCrossSection = powerCableCrossSection;
        this.rack = rack;
        this.connectingHardware = connectingHardware;
        this.hardware = hardware;
    }
}
