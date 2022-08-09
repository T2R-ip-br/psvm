package com.sukhoev.psms.rack.service;

import com.sukhoev.psms.rack.entity.RackConfiguration;
import com.sukhoev.psms.rack.repository.RackConfigurationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RackConfigurationService {

    private final RackConfigurationRepository rackConfigurationRepository;

    public void save(RackConfiguration rackConfiguration) {
        rackConfigurationRepository.save(rackConfiguration);
    }
}
