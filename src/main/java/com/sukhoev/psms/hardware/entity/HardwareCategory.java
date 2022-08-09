package com.sukhoev.psms.hardware.entity;

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
@Entity(name = "hardware_category")
public class HardwareCategory {

    @Id
    @SequenceGenerator(
            name = "hardware_category_sequence",
            sequenceName = "hardware_category_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "hardware_category_sequence"
    )
    private Long id;

    private String hardwareCategory;

    @OneToMany(
            mappedBy = "hardwareCategory",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<TypeHardware> typeHardware = new ArrayList<>();

    public HardwareCategory(String hardwareCategory) {

        this.hardwareCategory = hardwareCategory;
    }
}
