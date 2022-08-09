package com.sukhoev.psms.rack.service;

import com.sukhoev.psms.rack.comparators.UnitComparator;
import com.sukhoev.psms.rack.entity.Rack;
import com.sukhoev.psms.rack.entity.RackConfiguration;
import com.sukhoev.psms.rack.repository.RackConfigurationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class RackConfigurationService {

    private final RackConfigurationRepository rackConfigurationRepository;

    public void save(RackConfiguration rackConfiguration) {
        rackConfigurationRepository.save(rackConfiguration);
    }

    public List<RackConfiguration> findAllByRackId(Long rackId, int unitHeight, Rack rack) {

        List<RackConfiguration> rackConfigurations = rackConfigurationRepository.findAllByRackId(rackId);

        // Сохраняем номера занятых units
        List<Integer> occupiedUnit = new ArrayList<>();
        for (RackConfiguration rackConfiguration:
             rackConfigurations) {
            occupiedUnit.add(rackConfiguration.getOccupiedUnit());
        }

        // Дополняем список пустыми полями исключая занятые units
        for (int i = unitHeight; i > 0; i--) {
            if(!occupiedUnit.contains(i)) {
                rackConfigurations.add(new RackConfiguration("", i));
            }
        }

        // Сортируем список по убыванию номеров unit
        UnitComparator unitComparator = new UnitComparator();
        rackConfigurations.sort(unitComparator);

        return rackConfigurations;
    }
}
