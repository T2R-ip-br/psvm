package com.sukhoev.psms.rack.service;

import com.sukhoev.psms.rack.entity.ConnectingHardware;
import com.sukhoev.psms.rack.repository.ConnectingHardwareRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ConnectingHardwareService {

    private final ConnectingHardwareRepository connectingHardwareRepository;

    public List<ConnectingHardware> findAll() {

        List<ConnectingHardware> connectingHardwares = connectingHardwareRepository.findAll();

        if(connectingHardwares.isEmpty()) {
            throw new IllegalStateException("Connecting hardware not found");
        }

        return connectingHardwares;
    }

    public void save(ConnectingHardware connectingHardware) {
        connectingHardwareRepository.save(connectingHardware);
    }

    public List<ConnectingHardware> findAllByRackId(Long rackId) {
        List<ConnectingHardware> connectingHardwares = connectingHardwareRepository.findAllByRackId(rackId);

        if(connectingHardwares.isEmpty()) {
            throw new IllegalStateException("Connecting hardware not found");
        }

        return connectingHardwares;
    }
}
