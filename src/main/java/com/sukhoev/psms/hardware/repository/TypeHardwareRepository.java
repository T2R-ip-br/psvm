package com.sukhoev.psms.hardware.repository;

import com.sukhoev.psms.hardware.entity.TypeHardware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeHardwareRepository extends JpaRepository<TypeHardware, Long> {
}
