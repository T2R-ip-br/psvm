package com.sukhoev.psms.rack.service;

import com.sukhoev.psms.rack.entity.RackModel;
import com.sukhoev.psms.rack.repository.RackModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RackModelService {

    private RackModelRepository rackModelRepository;

    public List<RackModel> findAll() {
        List<RackModel> rackModels = rackModelRepository.findAll();

        if(rackModels.isEmpty()) {
            throw new IllegalStateException("Rack model not found");
        }

        return rackModels;
    }

    public void addRackModel(RackModel rackModel) {
        rackModelRepository.save(rackModel);
    }
}
