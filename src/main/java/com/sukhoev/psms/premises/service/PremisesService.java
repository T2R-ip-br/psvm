package com.sukhoev.psms.premises.service;

import com.sukhoev.psms.premises.entity.Premises;
import com.sukhoev.psms.premises.repository.PremisesRepository;
import com.sukhoev.psms.rack.entity.Rack;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PremisesService {

    private final PremisesRepository premisesRepository;

    public List<Premises> findAll() {
        List<Premises> premises = premisesRepository.findAll();

        if(premises.isEmpty()) {
            throw new IllegalStateException("premises not found");
        }

        return premises;
    }

    public List<Rack> findRacksByPremisesId(Long premisesId) {

        Optional<Premises> optionalPremises = premisesRepository.findById(premisesId);

        if(!optionalPremises.isPresent()) {
            throw new IllegalStateException("Room not found");
        }

        Premises premises = optionalPremises.get();

        List<Rack> racks = premises.getRacks();

        return racks;
    }

    public Premises findById(Long premisesId) {

        Optional<Premises> optionalPremises = premisesRepository.findById(premisesId);

        if(!optionalPremises.isPresent()) {
            throw new IllegalStateException("Room not found");
        }

        Premises premises = optionalPremises.get();

        return premises;
    }

    public void addPremises(Premises premises) {
        premisesRepository.save(premises);
    }
}
