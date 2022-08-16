package com.sukhoev.psms.rack.service;

import com.sukhoev.psms.hardware.entity.Hardware;
import com.sukhoev.psms.rack.comparators.OccupiedUnitComparator;
import com.sukhoev.psms.rack.comparators.UnitComparator;
import com.sukhoev.psms.rack.entity.Rack;
import com.sukhoev.psms.rack.entity.RackConfiguration;
import com.sukhoev.psms.rack.repository.RackConfigurationRepository;
import com.sukhoev.psms.rack.serviceСlass.OccupiedUnit;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class RackConfigurationService {

    private final RackConfigurationRepository rackConfigurationRepository;

    // Сохранение оборудования в стойке
    public void save(RackConfiguration rackConfiguration) {

        // id стойки
        Long rackId = rackConfiguration.getRack().getId();
        // Высота добавляемого оборудования в юнитах
        int heightAddedHardwareInUnits = rackConfiguration.getHardware().getRequiredNumberUnits();
        // Юнит планируемой установки оборудования
        int unitAddingHardware = rackConfiguration.getOccupiedUnit();
        // Высота стойки в юнитах
        int HeightRackInUnits = rackConfiguration.getRack().getRackModel().getUnitHeight();
        // Список установленного оборудования в стойку
        List<RackConfiguration> rackConfigurations = rackConfigurationRepository.findAllByRackId(rackId);
        // Допустимая ширина оборудования в стойке
        int hardwareWidthInRack = rackConfiguration.getRack().getRackModel().getEquipmentWidth();
        // Ширина добавляемого оборудования
        int hardwareWidth = rackConfiguration.getHardware().getWidth();
        // Допустимая глубина оборудования в стойке
        int hardwareDepthInRack = rackConfiguration.getRack().getRackModel().getUsableDepth();
        // Глубина оборудования
        int hardwareDepth = rackConfiguration.getHardware().getDepth();

        // Проверка на то, что оборудование соответствует по ширине
        if(hardwareWidthInRack != hardwareWidth) {
            throw new IllegalStateException("The equipment does not match in width");
        }

        // Проверка на то, что оборудование соответствует по глубине
        if(hardwareDepthInRack < hardwareDepth) {
            throw new IllegalStateException("The equipment does not match in depth");
        }

        // собираем список всех занятых юнитов
        List<Integer> occupiedUnits = new ArrayList<>();
        int unit;
        int heightInUnits;
        for (RackConfiguration configuration :
             rackConfigurations) {
            unit = configuration.getOccupiedUnit();
            heightInUnits = configuration.getHardware().getRequiredNumberUnits();
            if(heightInUnits == 1) {
                occupiedUnits.add(unit);
            } else {
                for(int i = unit; i > (unit - heightInUnits); i--) {
                    occupiedUnits.add(i);
                }
            }
        }

        // проверяем свободны ли юниты необходимые для добавления оборудования
        for(int i = unitAddingHardware; i > (unitAddingHardware - heightAddedHardwareInUnits); i--) {
            if(occupiedUnits.contains(i))
                throw new IllegalStateException("required unit/units are taken");
        }

        // Проверка на то, что оборудование помещается снизу стойки и не уходит ниже первого юнита
        if((unitAddingHardware - heightAddedHardwareInUnits) < 0) {
            throw new IllegalStateException("The height of the hardware is more than available when placed in this unit");
        }

        // Проверка на то, что оборудование размещается не выше верхнего юнита стойки
        if(unitAddingHardware > HeightRackInUnits) {
            throw new IllegalStateException("The specified hardware placement unit is above the upper rack unit");
        }



        // Проверка на оригинальность имём оборудования в стойке
        List<RackConfiguration> listAllHardwareNames = rackConfigurationRepository.findAllByRackId(rackId);
        for (RackConfiguration configuration:
                listAllHardwareNames) {

            if(rackConfiguration.getNameHardware().equals(configuration.getNameHardware())) {
                throw new IllegalStateException("This name taken");
            }
        }

        rackConfigurationRepository.save(rackConfiguration);
    }

    // Метод получения конфигурации стойки
    public List<RackConfiguration> findAllByRackId(Long rackId, int unitHeight) {

        List<RackConfiguration> rackConfigurations = rackConfigurationRepository.findAllByRackId(rackId);

        rackConfigurations = generateFullHeightRackConfiguration(rackConfigurations, unitHeight);

        // Сортируем список по убыванию номеров unit
        UnitComparator unitComparator = new UnitComparator();
        rackConfigurations.sort(unitComparator);

        return rackConfigurations;
    }


    // Метод формирвоания конфигурации стойки с учётом установленного оборудования, занятыми им юнитами и свободных юнитов
    private List<RackConfiguration> generateFullHeightRackConfiguration( List<RackConfiguration> initialRackConfiguration, int unitHeight) {

        // Сохраняем номера занятых units и высоту оборудования в юнитах
        List<OccupiedUnit> occupiedUnit = new ArrayList<>();
        for (RackConfiguration rackConfiguration:
                initialRackConfiguration) {

            occupiedUnit.add(new OccupiedUnit(
                    rackConfiguration.getOccupiedUnit(),
                    rackConfiguration.getHardware().getRequiredNumberUnits())
            );
        }

        // Сортируем список занятых юнитов по убыванию номеров unit
        OccupiedUnitComparator occupiedUnitComparator = new OccupiedUnitComparator();
        occupiedUnit.sort(occupiedUnitComparator);

        // Дополняем список пустыми полями исключая занятые units
        int requiredNumberUnits = 0; // <=0 : юнит свободен, >0 : юнит занят, выше было оборудование которое выше одного юнита
        for (int i = unitHeight; i > 0; i--) {
            if(!occupiedUnit.isEmpty()) { // Проверяем остались ли записи с занятыми юнитами
                if(!(occupiedUnit.get(0).getUpperUnit() == i)) { // проверяем занят юнит или нет, проверяем по верхниму юниту
                    if(requiredNumberUnits <= 0) // проверяем небыло ли оборудования которое занимает больше одного юнита
                        initialRackConfiguration.add(new RackConfiguration("", i, 101));
                    else
                        initialRackConfiguration.add(new RackConfiguration("", i, 100));
                    requiredNumberUnits--;
                } else {
                    requiredNumberUnits = occupiedUnit.get(0).getRequiredNumberOfUnits(); // записываем высоту оборудования
                    requiredNumberUnits--;
                    occupiedUnit.remove(0); // удаляем использованную запись
                }
            } else { // выполняется когда список с занятыми юнитами пуст
                if(requiredNumberUnits <= 0)
                    initialRackConfiguration.add(new RackConfiguration("", i, 101));
                else {
                    initialRackConfiguration.add(new RackConfiguration("", i, 100));
                    requiredNumberUnits--;
                }
            }
        }

        return initialRackConfiguration;
    }

    // получение конфигурации оборудования в стойке по id
    public RackConfiguration findById(Long rackConfigurationId) {

        Optional<RackConfiguration> optionalRackConfiguration = rackConfigurationRepository.findById(rackConfigurationId);

        if(!optionalRackConfiguration.isPresent()) {
            throw new IllegalStateException("rackConfiguration not found");
        }

        return optionalRackConfiguration.get();
    }

    public void deleteRackConfiguration(Long rackConfigurationId) {
        rackConfigurationRepository.deleteById(rackConfigurationId);
    }
}
