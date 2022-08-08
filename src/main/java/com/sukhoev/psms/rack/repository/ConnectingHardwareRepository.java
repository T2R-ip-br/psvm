package com.sukhoev.psms.rack.repository;

import com.sukhoev.psms.rack.entity.ConnectingHardware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectingHardwareRepository extends JpaRepository<ConnectingHardware, Long> {
}
