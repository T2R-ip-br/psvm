package com.sukhoev.psms.hardware.service;

import com.sukhoev.psms.hardware.entity.TypeCurrent;
import com.sukhoev.psms.hardware.repository.TypeCurrentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TypeCurrentService {

    private final TypeCurrentRepository typeCurrentRepository;

    public List<TypeCurrent> findAll() {
        return typeCurrentRepository.findAll();
    }
}
