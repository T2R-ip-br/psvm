package com.sukhoev.psms.rack.repository;

import com.sukhoev.psms.rack.entity.Rack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RackRepository extends JpaRepository<Rack, Long> {

}
