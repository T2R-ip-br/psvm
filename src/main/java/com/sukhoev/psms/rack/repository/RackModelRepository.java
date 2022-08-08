package com.sukhoev.psms.rack.repository;

import com.sukhoev.psms.rack.entity.RackModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RackModelRepository extends JpaRepository<RackModel, Long> {
}
