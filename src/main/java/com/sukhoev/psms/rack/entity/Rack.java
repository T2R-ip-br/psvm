package com.sukhoev.psms.rack.entity;

import com.sukhoev.psms.premises.entity.Premises;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
            mappedBy = "rack",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<RackConfiguration> rackConfigurations = new ArrayList<>();

    @OneToMany(
            mappedBy = "rack",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<ConnectingHardware> connectingHardware = new ArrayList<>();

    public Rack(
            String nameRack,
            Premises premises,
            RackModel rackModel
    ) {
        this.nameRack = nameRack;
        this.premises = premises;
        this.rackModel = rackModel;
    }

    @Override
    public String toString() {
        return "Rack{" +
                "id=" + id +
                ", nameRack='" + nameRack + '\'' +
                ", premises=" + premises +
                ", rackModel=" + rackModel +
                '}';
    }
}
