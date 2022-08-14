package com.sukhoev.psms.hardware.entity;

import com.sukhoev.psms.rack.entity.ConnectingHardware;
import com.sukhoev.psms.rack.entity.RackConfiguration;
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
@Entity(name = "hardware")
public class Hardware {

    @Id
    @SequenceGenerator(
            name = "hardware_sequence",
            sequenceName = "hardware_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "hardware_sequence"
    )
    private Long id;

    private String nameHardware;
    private Integer voltage;
    private Integer amperage;
    private Integer electricityConsumption;
    private Integer requiredNumberUnits;
    private Integer width;
    private Integer depth;

    @ManyToOne
    @JoinColumn(
            name = "type_hardware_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "type_hardware_fk"
            )
    )
    private TypeHardware typeHardware;

    @ManyToOne
    @JoinColumn(
            name = "type_current_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "type_current_fk"
            )
    )
    private TypeCurrent typeCurrent;

    @OneToMany(
            mappedBy = "hardware",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST},
            fetch = FetchType.LAZY
    )
    private List<RackConfiguration> rackConfigurations = new ArrayList<>();

    @OneToMany(
            mappedBy = "hardware",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST},
            fetch = FetchType.LAZY
    )
    private List<ConnectingHardware> connectingHardware = new ArrayList<>();

    public Hardware(
            String nameHardware,
            Integer voltage,
            Integer amperage,
            Integer electricityConsumption,
            Integer requiredNumberUnits,
            Integer width,
            Integer depth,
            TypeHardware typeHardware,
            TypeCurrent typeCurrent
    ) {
        this.nameHardware = nameHardware;
        this.voltage = voltage;
        this.amperage = amperage;
        this.electricityConsumption = electricityConsumption;
        this.requiredNumberUnits = requiredNumberUnits;
        this.width = width;
        this.depth = depth;
        this.typeHardware = typeHardware;
        this.typeCurrent = typeCurrent;
    }

    @Override
    public String toString() {
        return "Hardware{" +
                "id=" + id +
                ", nameHardware='" + nameHardware + '\'' +
                ", voltage=" + voltage +
                ", amperage=" + amperage +
                ", electricityConsumption=" + electricityConsumption +
                ", requiredNumberUnits=" + requiredNumberUnits +
                ", width=" + width +
                ", depth=" + depth +
                '}';
    }
}
