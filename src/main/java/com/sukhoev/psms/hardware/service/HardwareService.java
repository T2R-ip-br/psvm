package com.sukhoev.psms.hardware.service;

import com.sukhoev.psms.hardware.comparators.HardwareComparator;
import com.sukhoev.psms.hardware.entity.Hardware;
import com.sukhoev.psms.hardware.repository.HardwareRepository;
import com.sukhoev.psms.premises.entity.Premises;
import com.sukhoev.psms.rack.entity.Rack;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HardwareService {

    private final HardwareRepository hardwareRepository;

    public void save(Hardware hardware) {
        hardwareRepository.save(hardware);
    }

    public List<Hardware> findAll() {

        List<Hardware> hardware = hardwareRepository.findAll();

        if(hardware.isEmpty()) {
            throw new IllegalStateException("Hardware not found");
        }

        HardwareComparator hardwareComparator = new HardwareComparator();
        hardware.sort(hardwareComparator);

        return hardware;
    }

    public Hardware findById(Long hardwareId) {

        Optional<Hardware> optionalHardware = hardwareRepository.findById(hardwareId);

        if(!optionalHardware.isPresent()) {
            throw new IllegalStateException("Room not found");
        }

        Hardware hardware = optionalHardware.get();

        return hardware;
    }
}
