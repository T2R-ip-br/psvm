package com.sukhoev.psms.rack.repository;

import com.sukhoev.psms.rack.entity.RackConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RackConfigurationRepository extends JpaRepository<RackConfiguration, Long> {
    List<RackConfiguration> findAllById(Long rackId);

    List<RackConfiguration> findAllByRackId(Long rackId);

    RackConfiguration findByRackIdAndOccupiedUnit(Long rackId, Integer occupiedUnit);
}
