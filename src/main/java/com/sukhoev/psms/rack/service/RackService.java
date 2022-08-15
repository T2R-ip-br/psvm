package com.sukhoev.psms.rack.service;

import com.sukhoev.psms.rack.entity.Rack;
import com.sukhoev.psms.rack.repository.RackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RackService {

    private final RackRepository rackRepository;

    public Rack findById(Long rackId) {

        Optional<Rack> optRack = rackRepository.findById(rackId);

        if(!optRack.isPresent()) {
            throw new IllegalStateException("rack not found");
        }

        return optRack.get();
    }

    public void addRack(Rack rack) {
        rackRepository.save(rack);
    }

    public List<Rack> findAll() {

        List<Rack> racks = rackRepository.findAll();

        if(racks.isEmpty()) {
            throw new IllegalStateException("Rack not found");
        }

        return racks;
    }

    public void deleteRack(Long rackId) {
        rackRepository.deleteById(rackId);
    }
}
