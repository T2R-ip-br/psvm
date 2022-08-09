package com.sukhoev.psms.hardware.service;

import com.sukhoev.psms.hardware.entity.Hardware;
import com.sukhoev.psms.hardware.repository.HardwareRepository;
import com.sukhoev.psms.rack.entity.Rack;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

        return hardware;
    }
}
