package com.sukhoev.psms.rack.service;

import com.sukhoev.psms.rack.comparators.ConnectingHardwareComparator;
import com.sukhoev.psms.rack.entity.ConnectingHardware;
import com.sukhoev.psms.rack.entity.Rack;
import com.sukhoev.psms.rack.entity.RackConfiguration;
import com.sukhoev.psms.rack.repository.RackRepository;
import com.sukhoev.psms.rack.serviceСlass.Load;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RackService {

    private final RackRepository rackRepository;

    public Rack findById(Long rackId) {

        Optional<Rack> optRack = rackRepository.findById(rackId);

        if(!optRack.isPresent()) {
            throw new IllegalStateException("rack not found");
        }

        return optRack.get();
    }

    public void addRack(Rack rack) {
        rackRepository.save(rack);
    }

    public List<Rack> findAll() {

        List<Rack> racks = rackRepository.findAll();

        if(racks.isEmpty()) {
            throw new IllegalStateException("Rack not found");
        }

        return racks;
    }

    public void deleteRack(Long rackId) {
        rackRepository.deleteById(rackId);
    }

    // Получить список запаса нагрузки
    public List<Load> getAllLoad(Rack rack) {

        List<Load> load = new ArrayList<>();
        List<ConnectingHardware> connectingHardware = rack.getConnectingHardware();

        // запрос на запас мощности у стойки
        int reserveLoad = getReserveLoadRack(rack);

        load.add(new Load(
                "Запас стойки",
                rack.getRackModel().getMaximumLoad(),
                reserveLoad)
        );

        if(reserveLoad == rack.getRackModel().getMaximumLoad() )
            reserveLoad = (int) (rack.getRackModel().getMaximumLoad()*0.8);

        load.add(new Load(
                "Номинальный запас стойки",
                (int) (rack.getRackModel().getMaximumLoad()*0.8),
                reserveLoad)
        );

        ConnectingHardwareComparator connectingHardwareComparator = new ConnectingHardwareComparator();
        connectingHardware.sort(connectingHardwareComparator);

        for (ConnectingHardware connecting: connectingHardware) {

            // запрос на запас мощности у точки подключения
            reserveLoad = getReserveLoadConnectingHardware(connecting);

            load.add(new Load(
                    connecting.getNamePowerSupplyUnit(),
                    connecting.getHardware().getElectricityConsumption(),
                    reserveLoad)
            );
        }

        return load;
    }

    // Метод возвращает оставшийся запас мощности стойки
    public int getReserveLoadRack(Rack rack) {

        List<RackConfiguration> rackConfigurations = rack.getRackConfigurations();
        int reserveLoad = rack.getRackModel().getMaximumLoad();

        for (RackConfiguration rackConfiguration: rackConfigurations) {
            reserveLoad = reserveLoad - rackConfiguration.getHardware().getElectricityConsumption();
        }

        return reserveLoad;
    }

    public int getReserveLoadConnectingHardware(ConnectingHardware connectingHardware) {

        List<RackConfiguration> configurations = connectingHardware.getRackConfigurations();
        int reserveLoad = connectingHardware.getHardware().getElectricityConsumption();

        for (RackConfiguration rackConfiguration: configurations) {
            reserveLoad = reserveLoad - rackConfiguration.getHardware().getElectricityConsumption();
        }

        return reserveLoad;
    }
}
