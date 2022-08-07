package com.sukhoev.psms.rack.repository;

import com.sukhoev.psms.rack.entity.Rack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RackRepository extends JpaRepository<Rack, Long> {
}
