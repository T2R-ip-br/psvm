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
@Entity(name = "type_hardware")
public class TypeHardware {

    @Id
    @SequenceGenerator(
            name = "type_hardware_sequence",
            sequenceName = "type_hardware_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "type_hardware_sequence"
    )
    private Long id;

    private String typeHardware;

    @ManyToOne
    @JoinColumn(
            name = "hardware_category_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "hardware_category_fk"
            )
    )
    private HardwareCategory hardwareCategory;

    public TypeHardware(String typeHardware, HardwareCategory hardwareCategory) {
        this.typeHardware = typeHardware;
        this.hardwareCategory = hardwareCategory;
    }
}
