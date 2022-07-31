package com.sukhoev.psms.rack;

import com.sukhoev.psms.hardware.TypeHardware;
import com.sukhoev.psms.premises.Premises;
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
@Entity(name = "rack")
public class Racks {

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

    @ManyToOne
    @JoinColumn(
            name = "premises_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "premises_rack_fk"
            )
    )
    private Premises premises;

    @ManyToOne
    @JoinColumn(
            name = "rack_model_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "model_rack_fk"
            )
    )
    private RackModel rackModel;

    @OneToMany(
            mappedBy = "racks",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<RackConfiguration> rackConfigurations = new ArrayList<>();

    public Racks(
            String nameRack,
            Premises premises,
            RackModel rackModel
    ) {
        this.nameRack = nameRack;
        this.premises = premises;
        this.rackModel = rackModel;
    }
}
