package com.sukhoev.psms.hardware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeCurrent extends JpaRepository<TypeCurrent, Long> {
}
