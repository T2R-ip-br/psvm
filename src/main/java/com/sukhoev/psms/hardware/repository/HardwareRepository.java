package com.sukhoev.psms.hardware.repository;

import com.sukhoev.psms.hardware.entity.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardwareRepository extends JpaRepository<Hardware, Long> {
}
