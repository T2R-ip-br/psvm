package com.sukhoev.psms.hardware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sukhoev.psms.hardware.entity.TypeCurrent;

@Repository
public interface TypeCurrentRepository extends JpaRepository<TypeCurrent, Long> {
}
