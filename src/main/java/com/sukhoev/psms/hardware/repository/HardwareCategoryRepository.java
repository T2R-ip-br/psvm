package com.sukhoev.psms.hardware.repository;

import com.sukhoev.psms.hardware.entity.HardwareCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardwareCategoryRepository extends JpaRepository<HardwareCategory, Long> {
}
